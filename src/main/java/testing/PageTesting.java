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

    /**
     *
     */
    public static interface Action<T extends Element, X> {
        X apply(T t);
    }

    public static class IsAnyOptionSelected implements Action<CheckBox, Boolean> {
        @Override
        public Boolean apply(CheckBox checkBox) {
            for (Boolean b : checkBox.getOptions().values()) {
                if (b) return true;
            }
            return false;
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

        public Element findElement(String s) {
            return elements.stream().filter(e -> e.getName().equals(s)).findFirst().get();
        }
    }

    public static void main(String[] args) {
        Page mySuperAwesomePage = new Page();

        CheckBox gender = new CheckBox("gender");
        gender.addOptions("male", "female");

        mySuperAwesomePage.addElement(gender);

        //is any gender selected
        CheckBox checkBox = (CheckBox) mySuperAwesomePage.findElement("gender");


        IsAnyOptionSelected isAnyOptionSelected = new IsAnyOptionSelected();
        boolean b = isAnyOptionSelected.apply(checkBox);
        System.out.println(b);

    }

}
