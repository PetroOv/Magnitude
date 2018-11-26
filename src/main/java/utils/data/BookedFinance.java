package utils.data;

import com.github.javafaker.Faker;
import com.github.javafaker.Finance;
import com.github.javafaker.service.FakeValuesService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class BookedFinance extends Finance {
    private Faker faker;

    protected BookedFinance(Faker faker) {
        super(faker);
        this.faker = faker;
    }

    private static int sum(String[] string) {
        int sum = 0;
        String[] arr$ = string;
        int len$ = string.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            String s = arr$[i$];
            if (!s.isEmpty()) {
                sum += Integer.valueOf(s);
            }
        }

        return sum;
    }

    public String creditCard(String type) {
        String key = String.format("credit_card.%s", type.toLowerCase());
        String value = new FakeValuesService(Locale.ENGLISH, faker.random()).resolve(key, this, this.faker);
        String template = this.faker.numerify(value);
        String[] split = template.replaceAll("[^0-9]", "").split("");
        List<Integer> reversedAsInt = new ArrayList();

        int luhnSum;
        for (luhnSum = 0; luhnSum < split.length; ++luhnSum) {
            String current = split[split.length - 1 - luhnSum];
            if (!current.isEmpty()) {
                reversedAsInt.add(Integer.valueOf(current));
            }
        }

        luhnSum = 0;
        int multiplier = 1;

        Integer digit;
        for (Iterator i$ = reversedAsInt.iterator(); i$.hasNext(); luhnSum += sum(String.valueOf(digit * multiplier).split(""))) {
            digit = (Integer) i$.next();
            multiplier = multiplier == 2 ? 1 : 2;
        }

        int luhnDigit = (10 - luhnSum % 10) % 10;
        return template.replace('\\', ' ').replace('/', ' ').trim().replace('L', String.valueOf(luhnDigit).charAt(0));
    }
}
