#include <iostream>
#include <vector>
#include <string>
#include <random>
#include <print>
#include <ranges>
#include <algorithm>
class RandomGen{
    public:
        int randomInt(int min, int max) {
            std::random_device rd;
            std::mt19937 gen(rd());
            std::uniform_int_distribution<> dis(min, max);
            return dis(gen);
        }

        double randomDouble(double min, double max) {
            std::random_device rd;
            std::mt19937 gen(rd());
            std::uniform_real_distribution<> dis(min, max);
            return dis(gen);
        }
};

class Team{
    public:
        Team(std::string name) : name(name), goalsScored(0), goalsAgainst(0), yellowCards(0), redCards(0), topHeight(0), points(0) {}

        int goalDelta() const {
            return goalsScored - goalsAgainst;
        }
        //Setters
        void setGoalsScored(int goals){
            goalsScored=goals;
        }

        void setGoalsAgainst(int goals){
            goalsAgainst=goals;
        }

        void setYellowCards(int cards){
            yellowCards=cards;
        }

        void setRedCards(int cards){
            redCards=cards;
        }

        void setTopHeight(double height){
            topHeight=height;
        }

        void setPoints(double newPoints){
            points=newPoints;
        }

        //Getters
        int getGoalsScored() const {
            return goalsScored;
        }

        int getGoalsAgainst() const {
            return goalsAgainst;
        }

        int getYellowCards() const {
            return yellowCards;
        }

        int getRedCards() const {
            return redCards;
        }

        double getTopHeight() const {
            return topHeight;
        }

        double getPoints() const {
            return points;
        }

        std::string getName() const {
            return name;
        }
    private:
        std::string name;
        double points;
        int goalsScored;
        int goalsAgainst;
        int yellowCards;
        int redCards;
        double topHeight;
};


class Match {
public:
    Team& first;
    Team& second;
    void playMatch() {
        RandomGen rand;
        int goalsFirst = rand.randomInt(0, 5);
        int goalsSecond = rand.randomInt(0, 5);
        int yellowCardsFirst = rand.randomInt(0, 3);
        int yellowCardsSecond = rand.randomInt(0, 3);
        int redCardsFirst = rand.randomInt(0, 2);
        int redCardsSecond = rand.randomInt(0, 2);
        double topHeightFirst = rand.randomDouble(0.0, 20.0);
        double topHeightSecond = rand.randomDouble(0.0, 20.0);

        first.setGoalsScored(first.getGoalsScored()+goalsFirst);
        first.setGoalsAgainst(first.getGoalsAgainst()+goalsSecond);
        first.setYellowCards(first.getYellowCards()+yellowCardsFirst);
        first.setRedCards(first.getRedCards()+redCardsFirst);
        first.setTopHeight(first.getTopHeight()+topHeightFirst);

        second.setGoalsScored(second.getGoalsScored()+goalsSecond);
        second.setGoalsAgainst(second.getGoalsAgainst()+goalsFirst);
        second.setYellowCards(second.getYellowCards()+yellowCardsSecond);
        second.setRedCards(second.getRedCards()+redCardsSecond);
        second.setTopHeight(second.getTopHeight()+topHeightSecond);

        if (goalsFirst > goalsSecond) {
            first.setPoints(3.14+first.getPoints());
            second.setPoints(-0.5+second.getPoints());
        } else if (goalsFirst < goalsSecond) {
            second.setPoints(3.14+second.getPoints());
            first.setPoints(-0.5+first.getPoints());
        } else {
            first.setPoints(2.71828+first.getPoints());
            second.setPoints(2.71828+second.getPoints());
        }

    }
};

void printTeamStats(const Team& team) {
    std::println("name:{}\n points:{}\n goalsScored:{}\n goalsSkipped:{}\n delta:{}\n topHeight:{}\n yellowCards:{}\n redCards:{}", team.getName(),team.getPoints(),team.getGoalsScored(),team.getGoalsAgainst(),team.goalDelta(), team.getTopHeight(), team.getYellowCards(), team.getRedCards());
    std::println("--------------------");
};

bool compareTeams(const Team& first, const Team& second) {

    if (first.getPoints() != second.getPoints()) {
        return first.getPoints() > second.getPoints();
    }
    
    if (first.getTopHeight() != second.getTopHeight()) {
        return first.getTopHeight() > second.getTopHeight();
    }

    if (first.goalDelta() != second.goalDelta()) {
        return first.goalDelta() > second.goalDelta();
    }

    if (first.getGoalsScored() != second.getGoalsScored()) {
        return first.getGoalsScored() > second.getGoalsScored();
    }

    if (first.getRedCards() != second.getRedCards()) {
        return first.getRedCards() < second.getRedCards();
    }

    if (first.getYellowCards() != second.getYellowCards()) {
        return first.getYellowCards() < second.getYellowCards();
    }
    
    RandomGen rand;
    return rand.randomInt(0, 1) == 0;
};

int main() {
    std::vector<Team> teams;
    std::string teamName;

    for (int i = 0; i < 4; ++i) {
        std::cout << "Enter name for team " << i + 1 << ": ";
        std::cin >> teamName;
        teams.emplace_back(teamName);
    }

    std::vector<Match> matches;
    for (int i = 0; i < teams.size(); i++) {
        for (int j = i + 1; j < teams.size(); j++) {
            matches.emplace_back(teams[i], teams[j]);
        }
    }

    for (auto& match : matches) {
        match.playMatch();
    }

    std::ranges::stable_sort(teams, compareTeams);

    std::cout << "Tournament Results:\n";
    for (const auto& team : teams) {
        printTeamStats(team);
    }

    return 0;
}