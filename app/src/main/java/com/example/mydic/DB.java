package com.example.mydic;

public class DB {
    public static String[] getData(int id) {
        if (id == R.id.action_english) {
            return getEnglish();
        } else if (id == R.id.action_amharic) {
            return getAmharic();
        } else if (id == R.id.action_tigrigna) {
            return getTigrigna();
        }
        return new String[0];
    }

    public static String[] getEnglish() {
        String[] source = new String[]{
                "abandon",
                "ability",
                "able",
                "abortion",
                "about",
                "above",
                "abroad",
                "absence",
                "absolute",
                "absolutely",
                "absorb",
                "abuse",
                "academic",
                "accept",
                "access",
                "baseball",
                "basic",
                "basically",
                "basis",
                "basket",
                "basketball",
                "bathroom",
                "battery",
                "battle",
                "be",
                "beach",
                "bean",
                "bear",
                "beat",
                "beautiful",
                "beauty",
                "because",
                "become",
                "bed",
                "bedroom",
                "beer",
                "before",
                "begin",
                "beginning",
                "behavior",
                "behind"
        };
        return source;
    }

    public static String[] getAmharic() {
        String[] source = new String[]{
                "ከሚነሡ",
                "ጉዳይ",
                "ቅዱሳን",
                "ክብራቸውን",
                "ቅባት",
                "ቀድተው",
                "ክህነታዊ",
                "ሕይወት",
                "የሐዋርያት",
                "መንፈሳዊ",
                "ለመግለጥ",
                "ሃይማኖት",
                "ሚካኤል",
                "ትቶ",
                "ትምህርት",
                "ኪርኤ አላይሶን"
        };
        return source;
    }

    public static String[] getTigrigna() {
        String[] source = new String[]{

                "ሂባ",
                "ቅዱሳን",
                "ወሲዱ",
                "ቅባት",
                "ቀድተው",
                "ክህነታዊ",
                "ሕይወት",
                "የሐዋርያት",
                "መንፈሳዊ",
                "ለመግለጥ",
                "ሃይማኖት",
                "ሚካኤል",
                "ትቶ",
                "ትምህርት",
                "ኪርኤ አላይሶን"
        };
        return source;
    }
}
