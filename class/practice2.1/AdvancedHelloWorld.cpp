#include <string>
#include <iostream>
#include <fstream>
#include <sstream>
#include <map>

namespace {
    const std::string bread = "bread";
    const std::string deleteCommand = "delete";
}
class Database {
private:
    std::map<std::string, int> statsData;
    std::string database;
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
 std::string deleteUser(const std::string& nameToFind) {
    auto name = statsData.find(nameToFind);
    if (name != statsData.end()) {
        statsData.erase(name);
    }
    SaveData();
    return "All data about this user was cleared";
}

    std::string UserIncrement(const std::string& nameToFind) {
        std::string returnValue;
        if (statsData.find(nameToFind)!=statsData.end())
        {
            statsData [nameToFind]++;
            returnValue="Hello again(x" + std::to_string(statsData[nameToFind]) + ")," + nameToFind;
        }else{
            statsData [nameToFind]++;
            returnValue="Welcome " +nameToFind;
        }
        SaveData();
        return returnValue;
    }
public:
    Database(const std::string& filePath) : database(filePath) {
         LoadData();
    }
    void clearDatabase() {
        statsData.clear();
        SaveData();
    }
    std::string PerformChanges(const std::string& nameToFind, const std::string& additionalCommand) {
        if (statsData.find(nameToFind)==statsData.end()) {
            if (additionalCommand == deleteCommand) {
               return "There is no such user ";
            }
            return UserIncrement(nameToFind);
        }
        else {
            if (additionalCommand == deleteCommand) {
                return deleteUser(nameToFind);
            }
            return UserIncrement(nameToFind);
        }
    }
};
int main(int argc, char* argv[])
{
    Database db("database.txt");
    if (argc < 2) {
        std::cout << "Please provide a name to find." << std::endl;
        return 0;
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
   std::cout << db.PerformChanges(nameToFind,additionalCommand)<< std::endl;
    return 0;
}
