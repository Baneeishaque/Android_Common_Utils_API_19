package ndk.utils_android19;

public class StringUtils {

    public static String removeQuotations(String string) {
        return removeSymbol(string, "\"");
    }

    private static String removeSymbol(String string, String symbol) {
        return string.replace(symbol, "");
    }
}
