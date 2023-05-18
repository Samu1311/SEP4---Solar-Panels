import Model.*;
public class Test
{
  public static void main(String[] args)
  {
    QuantitativeFactor factor1 = new QuantitativeFactor("gdp",0,15,true,10000000);
    QuantitativeFactor factor2 = new QuantitativeFactor("Growth",0,20,false,10000000);
    QualitativeFactor factor3 = new QualitativeFactor("gdp",0,20,true, QualitativeValue.QualitativeValues.BAD);

    QuantitativeFactorList list = new QuantitativeFactorList();
    list.addFactor(factor1);
    list.addFactor(factor2);

    QualitativeFactorList list2 = new QualitativeFactorList();
    list2.addFactor(factor3);


    Country country1 = new Country("Australia",list2, list);
    Country country2 = new Country("Austria",list2, list);
    Country country3 = new Country("China",list2, list);

    country2.setQuantitativeValueMA(0,20000000);
    country3.setQuantitativeValueMA(0,30000000);
    country2.setQuantitativeValueMA(1,20000000);
    country3.setQuantitativeValueMA(1,30000000);

    CountryList listOfCountries = new CountryList();
    listOfCountries.addCountry(country1);
    listOfCountries.addCountry(country2);
    listOfCountries.addCountry(country3);

    listOfCountries.defineRange();

    listOfCountries.assignQuantitativePoints();
    listOfCountries.assignQualitativePoints();

    listOfCountries.weightedMAPointsCalculation();
    listOfCountries.weightedCSPointsCalculation();
    System.out.println("Country1");
    System.out.println(listOfCountries.getCountry(0).getMarketAttractivenessPunctuation());
    System.out.println(listOfCountries.getCountry(0).getCompetitiveStrengthPunctuation());
    System.out.println("Country2");
    System.out.println(listOfCountries.getCountry(1).getMarketAttractivenessPunctuation());
    System.out.println(listOfCountries.getCountry(1).getCompetitiveStrengthPunctuation());
    System.out.println("Country3");
    System.out.println(listOfCountries.getCountry(2).getMarketAttractivenessPunctuation());
    System.out.println(listOfCountries.getCountry(2).getCompetitiveStrengthPunctuation());





  }
}
