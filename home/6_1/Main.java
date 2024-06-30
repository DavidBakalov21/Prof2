import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

class GreenMessage {
    String message;
    int counter;

    GreenMessage(String message, int counter) {
        this.message = message;
        this.counter = counter;
    }
}

class BlueMessage {
    double val1;
    double val2;

    BlueMessage(double val1, double val2) {
        this.val1 = val1;
        this.val2 = val2;
    }
}

class OrangeMessage {
    String message1;
    String message2;
    int counter;
    double val;

    OrangeMessage(String message1, String message2, int counter, double val) {
        this.message1 = message1;
        this.message2 = message2;
        this.counter = counter;
        this.val = val;
    }
}

class Subscription<T> {
    private final UUID id;
    private final Consumer<T> subscriber;

    Subscription(UUID id, Consumer<T> subscriber) {
        this.id = id;
        this.subscriber = subscriber;
    }

    UUID getId() {
        return id;
    }

    Consumer<T> getSubscriber() {
        return subscriber;
    }
}

class MessageDispatcher {
    private final Lock lock = new ReentrantLock();
    private final List<Subscription<GreenMessage>> greenSubscribers = new ArrayList<>();
    private final List<Subscription<BlueMessage>> blueSubscribers = new ArrayList<>();
    private final List<Subscription<OrangeMessage>> orangeSubscribers = new ArrayList<>();

    UUID subscribeGreen(Consumer<GreenMessage> subscriber) {
        lock.lock();
        try {
            UUID id = UUID.randomUUID();
            greenSubscribers.add(new Subscription<>(id, subscriber));
            return id;
        } finally {
            lock.unlock();
        }
    }

    UUID subscribeBlue(Consumer<BlueMessage> subscriber) {
        lock.lock();
        try {
            UUID id = UUID.randomUUID();
            blueSubscribers.add(new Subscription<>(id, subscriber));
            return id;
        } finally {
            lock.unlock();
        }
    }

    UUID subscribeOrange(Consumer<OrangeMessage> subscriber) {
        lock.lock();
        try {
            UUID id = UUID.randomUUID();
            orangeSubscribers.add(new Subscription<>(id, subscriber));
            return id;
        } finally {
            lock.unlock();
        }
    }

    void unsubscribeGreen(UUID id) {
        lock.lock();
        try {
            greenSubscribers.removeIf(sub -> sub.getId().equals(id));
        } finally {
            lock.unlock();
        }
    }

    void unsubscribeBlue(UUID id) {
        lock.lock();
        try {
            blueSubscribers.removeIf(sub -> sub.getId().equals(id));
        } finally {
            lock.unlock();
        }
    }

    void unsubscribeOrange(UUID id) {
        lock.lock();
        try {
            orangeSubscribers.removeIf(sub -> sub.getId().equals(id));
        } finally {
            lock.unlock();
        }
    }

    void publishGreen(GreenMessage message) {
        lock.lock();
        try {
            for (Subscription<GreenMessage> sub : greenSubscribers) {
                sub.getSubscriber().accept(message);
            }
        } finally {
            lock.unlock();
        }
    }

    void publishBlue(BlueMessage message) {
        lock.lock();
        try {
            for (Subscription<BlueMessage> sub : blueSubscribers) {
                sub.getSubscriber().accept(message);
            }
        } finally {
            lock.unlock();
        }
    }

    void publishOrange(OrangeMessage message) {
        lock.lock();
        try {
            for (Subscription<OrangeMessage> sub : orangeSubscribers) {
                sub.getSubscriber().accept(message);
            }
        } finally {
            lock.unlock();
        }
    }
}

class MessageGenerator implements Runnable {
    private final MessageDispatcher dispatcher;
    private final Random random = new Random();

    MessageGenerator(MessageDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            int messageType = random.nextInt(3);
            if (messageType == 0) {
                GreenMessage msg = new GreenMessage("Green", random.nextInt(3));
                dispatcher.publishGreen(msg);
            } else if (messageType == 1) {
                BlueMessage msg = new BlueMessage(random.nextDouble(), random.nextDouble() * 2.0);
                dispatcher.publishBlue(msg);
            } else {
                OrangeMessage msg = new OrangeMessage("Orange1", "Orange2", random.nextInt(3), random.nextDouble() * 1.5);
                dispatcher.publishOrange(msg);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MessageDispatcher dispatcher = new MessageDispatcher();

        UUID greenSubId = dispatcher.subscribeGreen(msg -> System.out.println("GreenMessage: " + msg.message + ", " + msg.counter));
        UUID blueSubId = dispatcher.subscribeBlue(msg -> System.out.println("BlueMessage: " + msg.val1 + ", " + msg.val2));
        UUID orangeSubId = dispatcher.subscribeOrange(msg -> System.out.println("OrangeMessage: " + msg.message1 + ", " + msg.message2 + ", " + msg.counter + ", " + msg.val));

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new MessageGenerator(dispatcher));
        executorService.submit(new MessageGenerator(dispatcher));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            dispatcher.unsubscribeGreen(greenSubId);
            dispatcher.unsubscribeBlue(blueSubId);
            dispatcher.unsubscribeOrange(orangeSubId);

            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }));
        try {
            while (true) {
                Thread.sleep(Long.MAX_VALUE);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
