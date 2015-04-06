package mathtree;
//         _____  __                __     ______
// .-----.|  |  ||  |--.----.---.-.|  |--.|__    |
// |     ||__    |  _  |  __|  _  ||    < |__    |
// |__|__|   |__||_____|____|___._||__|__||______|
//                                       4 project


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class MathTreeNode{
        double value = Double.NaN;
        final String name;
        public List<MathTreeNode> children;


        public MathTreeNode(String name, List<MathTreeNode> children) {
            this.name = name;
            this.children = children;
            for (MathTreeNode elem : children){
                if (Double.isNaN(value)){
                    value = elem.value;
                    continue;
                }
                if (name.equals("-"))
                    this.value -= elem.value;
                if (name.equals("+"))
                    this.value += elem.value;
                if (name.equals("/"))
                    this.value /= elem.value;
                if (name.equals("*"))
                    this.value *= elem.value;
            }
        }

    private void calculate() {

    }


    public MathTreeNode(String name) {
            this.name = name;
                if (name.matches("\\d++"))
                    this.value = Double.parseDouble(name);
            this.children = new ArrayList<MathTreeNode>();
        }

    public void print() {
        System.out.println("="+new DecimalFormat("#.##").format(value));
            print("", true);
        }
    public boolean isState() {
        return (name.matches("[-+/*]") && this.children.size() < 1);
    }
    public boolean numbornode(){
        return (!isState() || this.children.size() > 1);
    }

        private void print(String prefix, boolean isTail) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + name);
            for (int i = 0; i < children.size() - 1; i++) {
                children.get(i).print(prefix + (isTail ? "    " : "│   "), false);
            }
            if (children.size() > 0) {
                children.get(children.size() - 1).print(prefix + (isTail ?"    " : "│   "), true);
            }
        }
}
