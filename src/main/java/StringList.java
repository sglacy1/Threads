import java.util.List;
import java.util.ArrayList;

class StringList {

    List<String> strings = new ArrayList<String>();


    public void add(String str) {
        int pos = strings.indexOf(str);
        if (pos < 0) {
            strings.add(str);

        }
    }

    public boolean contains(String str) {
        return strings.indexOf(str) >= 0;
    }

    public int size() {
        return strings.size();
    }

    public String toString() {
        return strings.toString();
    }

    public void remove(int index) {
        strings.remove(index);
    }

    public String remove(StringList strings, int index) {
        if (strings == null || strings.size() <= index) {
            return null;
        }
        if (index >= 0 && index < strings.size()) {
            strings.remove(index);
        }
        return strings.toString();
    }

    public String reverse(int index) {
        String stringToReverse = strings.get(index);
        String reversedString = "";


            if (strings == null || strings.size() <= index) {
                return "That index does not exist";
            }
            if (index >= 0 && index < strings.size()) {

                for (int i = stringToReverse.length() - 1; i >= 0; i--) {
                    reversedString = reversedString + stringToReverse.charAt(i);
                }
            }
            return reversedString.toString();

        }

    public String count() {

        ArrayList<Integer> countArray = new ArrayList<>();
            for(String item: strings) {
                countArray.add(item.length());
        }
        return countArray.toString();
    }

}
