package com.blogspot.toomuchcoding.book.chapter2._7_MockingEnums.assertj;

import com.blogspot.toomuchcoding.book.chapter2._7_MockingEnums.Country;
import com.blogspot.toomuchcoding.book.chapter2._7_MockingEnums.TaxFactorCalculator;
import com.blogspot.toomuchcoding.person.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Country.class)
public class TaxFactorCalculatorTest {

    static final double TAX_FACTOR = 10000;

    @Mock Country country;

    @InjectMocks
    TaxFactorCalculator systemUnderTest;

    @Test
    public void should_calculate_tax_factor() {
        // given
        mockStatic(Country.class);
        given(Country.fromCountryName(anyString())).willReturn(country);
        given(country.calculateTaxFactorFor(any(Person.class))).willReturn(TAX_FACTOR);

        // when
        double taxFactorForPerson = systemUnderTest.calculateTaxFactorFor(new Person());

        // then
        then(taxFactorForPerson).isEqualTo(TAX_FACTOR);
    }

}
