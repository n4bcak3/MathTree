package mathtree;//         _____  __                __     ______
// .-----.|  |  ||  |--.----.---.-.|  |--.|__    |
// |     ||__    |  _  |  __|  _  ||    < |__    |
// |__|__|   |__||_____|____|___._||__|__||______|
//

import jdk.nashorn.internal.runtime.regexp.RegExp;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class MathTree extends RecursiveTask<MathTree>{
    public List<MathTreeNode> branches;

    public MathTree(List<MathTreeNode> b) {
        this.branches = b;
    }

    @Override
    protected MathTree compute() {
        if (this.branches.size() < 2){
            return this;
        }

        List<MathTreeNode> new_branches = wrap();

        MathTree mt = new MathTree(new_branches);

        return mt.compute();
    }

    public List<MathTreeNode> wrap(){
        List<MathTreeNode> new_branches = new ArrayList<MathTreeNode>();

        for (MathTreeNode elem : this.branches){

            if (elem.isState()){
                int index = this.branches.indexOf(elem);
                if (index>1){
                    if ( this.branches.get(index-1).numbornode() &&
                            this.branches.get(index-2).numbornode() ){
                        List<MathTreeNode> childs = new ArrayList<MathTreeNode>();
                        childs.add(new_branches.remove(new_branches.size()-1));
                        childs.add(new_branches.remove(new_branches.size()-1));
                        MathTreeNode node = new MathTreeNode(elem.name,childs);
                        new_branches.add(node);
                    } else
                        new_branches.add(elem);
                }
            }
            else
                new_branches.add(elem);
        }

        return new_branches;
    }

    public void print() {
        this.branches.get(0).print();
    }

    public String printraw(){
        String s = "";
        for (MathTreeNode elem : this.branches)
            s+=elem.name;
        return s;
    }
}
