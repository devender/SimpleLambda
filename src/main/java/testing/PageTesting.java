package testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dgollapally on 12/12/16.
 */
public class PageTesting {

    /**
     *
     */
    public static interface Element {
        String getName();
    }

    /**
     *
     */
    public static class CheckBox implements Element {
        private final String name;
        private final Map<String, Boolean> options = new HashMap<>();

        public CheckBox(String name) {
            this.name = name;
        }

        public void addOptions(String... optionLabels) {
            for (String option : optionLabels) {
                options.put(option, false);
            }
        }

        public Map<String, Boolean> getOptions() {
            return options;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    public static class IsAnyOptionSelected {

        public static Boolean apply(CheckBox checkBox) {
            for (Boolean b : checkBox.getOptions().values()) {
                if (b) return true;
            }
            return false;
        }
    }

    public static class SelectOption {
        public static void apply(CheckBox checkBox, String option) {
            if (checkBox.getOptions().containsKey(option)) {
                checkBox.getOptions().put(option, true);
            }
        }
    }

    public static class IsOptionSelected {
        public static Boolean apply(CheckBox checkBox, String option) {
            return checkBox.getOptions().containsKey(option) && checkBox.getOptions().get(option);
        }
    }

    /**
     *
     */
    public static class Page {
        private List<Element> elements = new ArrayList<>();

        public void addElement(Element element) {
            elements.add(element);
        }

        public <T extends Element> T findElement(String s) {
            for (Element element : elements) {
                if (element.getName().equals(s)) {
                    return (T) element;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Page mySuperAwesomePage = new Page();

        CheckBox gender = new CheckBox("gender");
        gender.addOptions("male", "female");

        mySuperAwesomePage.addElement(gender);

        //is any gender selected
        CheckBox checkBox = mySuperAwesomePage.findElement("gender");

        System.out.println(IsAnyOptionSelected.apply(checkBox));

        SelectOption.apply(checkBox, "male");

        System.out.println(IsAnyOptionSelected.apply(checkBox));

    }

}
