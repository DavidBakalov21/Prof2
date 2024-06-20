#pragma once
#include <memory>
#include <string>
#include "ModernCalculators.hpp"
#include "LegacyCalculator.hpp"

class MegaAdapter : public BaseMegaCalculator {
public:
    MegaAdapter(std::unique_ptr<LegacyCalculator> legacyCalculator)
        : LegacyCalculatorField(std::move(legacyCalculator)) {}

    double getPrice() const override {
        return LegacyCalculatorField->calculatePricePart1() + LegacyCalculatorField->calculatePricePart2();
    }

    double getMinimalValue() const override {
        return LegacyCalculatorField->getOurTheMostAndMinimalValue();
    }

    std::string getReport() const override {
        return LegacyCalculatorField->getSomeDocumentRepresentation();
    }

private:
    std::unique_ptr<LegacyCalculator> LegacyCalculatorField;
};
