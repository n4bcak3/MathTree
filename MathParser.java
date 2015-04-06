package mathtree;//         _____  __                __     ______
// .-----.|  |  ||  |--.----.---.-.|  |--.|__    |
// |     ||__    |  _  |  __|  _  ||    < |__    |
// |__|__|   |__||_____|____|___._||__|__||______|
//                                       4 project


import mathtree.MathTree;
import mathtree.MathTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ForkJoinPool;

public class MathParser {
    private MathTree parsed_tree;
    private String raw_function;
    private String parsed_function;
    private List<MathTreeNode> tree = new ArrayList<MathTreeNode>();

    public MathParser(String f) {
        this.raw_function = f;
    }

    public void printTree() {
        try {
            this.parsed_tree.print();
        } catch (Exception e){}
    }
    public int isparsed(){
        return this.parsed_tree.branches.size();
    }

    public String parse() {
        ArrayList<String> out = new ArrayList<String>();
        Stack<String> buff = new Stack<String>();
        String z;

        // input array
        String[] f = this.raw_function.split("");

        for (int i=0;i<f.length;i++){
            if (f[i].equals(" ")) continue;
            else
                z = f[i];

            if (z.matches("(\\d+)")) {
                out.add(z);
            }
            else {
                if (z.matches("[/*+-]"))
                    try {
                        if (buff.get(buff.size() - 1).matches("[*/]"))
                            out.add(buff.pop());
                    } catch (Exception e){}

                if (z.matches("\\)")) {
                    String zscopes = buff.pop();
                    while (!zscopes.matches("\\(")){
                        out.add(zscopes);
                        zscopes = buff.pop();
                    }
                    continue;
                }
                buff.push(z);
            }
        }
        while (!buff.isEmpty()){
            out.add(buff.pop());
        }

        String msg = "";
        for (String s : out){
            msg += s;
        }
        return msg;
    }

    public void calculate() {
        try {
            this.parsed_function = parse();
        } catch (Exception e){
            System.out.println("\u001B[31mParsing failed !\u001B[0m");
        }
//        System.out.println(this.raw_function);
        System.out.println("Parsed equation: "+parsed_function);

        String[] elements = this.parsed_function.trim().split("");
        for (String elem : elements)
            this.tree.add(new MathTreeNode(elem));

        MathTree mt = new MathTree(this.tree);
        ForkJoinPool pool = new ForkJoinPool();
        try {
            this.parsed_tree = pool.invoke(mt);
        } catch (StackOverflowError e){
            System.out.println("\u001B[31mError! Signature is invalid.\u001B[0m");
        }
    }
}
