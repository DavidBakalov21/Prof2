#include <string>
#include <iostream>
#include <fstream>
#include <sstream>
#include <map>
#include <format>
namespace {
    const std::string bread = "bread";
    const std::string deleteCommand = "delete";
}
class Database {

public:
    Database(const std::string& filePath) : database(filePath) {
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
         auto count = statsData[nameToFind];
         return count;
    }
    std::map<std::string, int> getStatsData(){
        return statsData;
    }
private:
    std::map<std::string, int> statsData;
    const std::string database;
    void LoadData() {
        std::ifstream file(database);
        std::string name;
        int count;
        while (file >> name >> count) {
            statsData [name] = count;
        }
        file.close();
    }
    void SaveData() {
        std::ofstream file(database, std::ios::trunc);
        for (const auto& [name, count] : statsData ) {
            file << name << " " << count << std::endl;
        }
        file.close();
    }
};
int main(int argc, char* argv[])
{
    Database db("database.txt");

    if (argc < 2) {
        std::cout << "Please provide a name to find." << std::endl;
        return 1;
    }

    std::string nameToFind;
    nameToFind = argv[1];
    const auto additionalCommand = std::string(argc > 2 ? argv[2] : "");

    if (nameToFind == bread)
    {
        db.clearDatabase();
        std::cout << "all data was cleared" << std::endl;
        return 0;
    }

    std::map<std::string, int> statsData=db.getStatsData();
    if (statsData.find(nameToFind)==statsData.end()) {
        if (additionalCommand == deleteCommand) {
           std::cout <<"There is no such user "<<std::endl;
           return 1;
        }
     db.UserIncrement(nameToFind);
     std::cout <<"Welcome " <<nameToFind<<std::endl;
    }
    else {
        if (additionalCommand == deleteCommand) {
            if (db.deleteUser(nameToFind)==true){
                std::cout << "All data about this user was cleared"<<std::endl;
                return 0;
            }
            std::cout << "There was no such user"<<std::endl;
            return 1;
        }
        std::cout<<"Hello again(x" << db.UserIncrement(nameToFind) << ")," << nameToFind<<std::endl;
    }
    return 0;
}
