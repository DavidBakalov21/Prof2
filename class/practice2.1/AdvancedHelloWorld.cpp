#include <string>
#include <iostream>
#include <fstream>
#include <sstream>
#include <map>
#include <print>
namespace {
   constexpr const char* bread = "bread";
   constexpr const char* deleteCommand = "delete";
}
class Database {

public:
    Database(const std::string& filePath) : databasePath(filePath) {
         LoadData();
    }
    void clearDatabase() {
        statsData.clear();
        SaveData();
    }

    bool deleteUser(const std::string& nameToFind) {
        auto nameIt = statsData.find(nameToFind);
        if (nameIt != statsData.end()) {
            statsData.erase(nameIt);
            SaveData();
            return true;
        }
        return false;
    }

    int UserIncrement(const std::string& nameToFind) {
         statsData[nameToFind]++;
         SaveData();
         return statsData[nameToFind];
    }
    bool checkName(const std::string& nameToFind){
        return statsData.find(nameToFind)!=statsData.end();
    }
private:
    std::map<std::string, int> statsData;
    const std::string databasePath;
    void LoadData() {
        std::ifstream file(databasePath);
        std::string name;
        int count;
        while (file >> name >> count) {
            statsData [name] = count;
        }
        file.close();
    }
    void SaveData() {
        std::ofstream file(databasePath, std::ios::trunc);
        for (const auto& [name, count] : statsData ) {
            file << name << " " << count << std::endl;
        }
        file.close();
    }
};

int notFoundNameHandling(const std::string& additionalCommand, const std::string& nameToFind, Database& db ){
    if (additionalCommand == deleteCommand) {
        std::println("There is no such user ");
        return 1;
    }
    db.UserIncrement(nameToFind);
    std::println("Welcome {}",nameToFind);
    return 0;
}

int FoundNameHandling(const std::string& additionalCommand, const std::string& nameToFind, Database& db ){
    if (additionalCommand == deleteCommand) {
        if (db.deleteUser(nameToFind)==true){
            std::println("All data about this user was cleared");
            return 0;
        }
        std::println("There was no such user");
        return 1;
    }
    std::println("Hello again(x{}),{}", db.UserIncrement(nameToFind), nameToFind);
    return 0;
}

int main(int argc, char* argv[])
{
    Database db("database.txt");

    if (argc < 2) {
        std::println("Please provide a name to find.");
        return 1;
    }

    std::string nameToFind;
    nameToFind = argv[1];
    const auto additionalCommand = std::string(argc > 2 ? argv[2] : "");

    if (nameToFind == bread)
    {
        db.clearDatabase();
        std::println("all data was cleared");
        return 0;
    }

    if (!db.checkName(nameToFind)) {
        return notFoundNameHandling(additionalCommand,nameToFind,db);
    }
    else {
        return FoundNameHandling(additionalCommand,nameToFind,db);
    }
    return 0;
}
