import org.junit.jupiter.api.Test;  
import static org.junit.jupiter.api.Assertions.*;  
  
import java.util.Optional;  

public class PoolTest {  
  
    @Test  
    public void GetThenRelease() {  
        //build  
        MegaDataPool pool = new MegaDataPool(3);
  
        //operate  
        Optional<MegaData> result = pool.acquire();
         pool.release(result.get());
  
        //check  
       assertEquals(0,pool.usedSize());
    }  
  
    @Test  
    public void DoNotRelease() {  
        //build  
        MegaDataPool pool = new MegaDataPool(3);
  
        //operate  
        Optional<MegaData> result = pool.acquire();
  
        //check  
       assertEquals(1,pool.usedSize()); 
      
    }

    @Test  
    public void NoData() {  
        //build  
        MegaDataPool pool = new MegaDataPool(0);
  
        //operate  
        Optional<MegaData> result = pool.acquire();
  
        //check  
        assertFalse(result.isPresent()); 
      
    }    
}