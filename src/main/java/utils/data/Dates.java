package utils.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public final class Dates {
    public static final String TEST_DAY;
    public static final String END_DAY_FORMATTED;
    public static final String END_DAY_FORMATTED_INPUT;
    public static final String END_DAY_FORMATTED_CLICKUNDER;
    public static final String START_DAY_FORMATTED;
    public static final String START_DAY_FORMATTED_INPUT;
    public static final String START_DAY_FORMATTED_CLICKUNDER;
    public static final Calendar START_DAY;
    public static final Calendar END_DAY;

    static {
        START_DAY = Calendar.getInstance();
        END_DAY = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE d MMM yyyy", Locale.ENGLISH);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MMM-d", Locale.ENGLISH);
        SimpleDateFormat simpleDateFormatForClickUnder = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        TEST_DAY = simpleDateFormat.format(START_DAY.getTime());
        START_DAY.add(Calendar.DATE, 1);
        END_DAY.add(Calendar.DATE, 2);
        START_DAY_FORMATTED = simpleDateFormat.format(START_DAY.getTime());
        START_DAY_FORMATTED_INPUT = simpleDateFormat2.format(START_DAY.getTime());
        START_DAY_FORMATTED_CLICKUNDER = simpleDateFormatForClickUnder.format(START_DAY.getTime());
        END_DAY_FORMATTED = simpleDateFormat.format(END_DAY.getTime());
        END_DAY_FORMATTED_INPUT = simpleDateFormat2.format(END_DAY.getTime());
        END_DAY_FORMATTED_CLICKUNDER = simpleDateFormatForClickUnder.format(END_DAY.getTime());
    }
}
