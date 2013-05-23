package alex.algorithms.math.projecteuler.level4;

import static java.lang.Math.abs;
import static java.lang.Math.round;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Euler093 {
    static boolean next(final int[] nums, final int pos) {
        if (++nums[pos] <= 9 - (nums.length - 1 - pos))
            return true;
        if (pos == 0)
            return false;
        if (!next(nums, pos - 1))
            return false;
        nums[pos] = nums[pos - 1] + 1;
        return true;
    }

    static List<Double> makeFrom(final List<Double> frm, final double elem) {
        List<Double> ret = new ArrayList<Double>();
        ret.addAll(frm);
        ret.add(elem);
        return ret;
    }

    static Set<List<Double>> reduce(final List<Double> s) {
        Set<List<Double>> ret = new HashSet<List<Double>>();
        for (int i = 0; i < s.size(); i++) {
            for (int j = i + 1; j < s.size(); j++) {
                List<Double> newList = new ArrayList<Double>();
                for (int k = 0; k < s.size(); k++) {
                    if (k == i || k == j)
                        continue;
                    newList.add(s.get(k));
                }
                double n1 = s.get(i);
                double n2 = s.get(j);
                ret.add(makeFrom(newList, n1 + n2));
                ret.add(makeFrom(newList, n1 - n2));
                ret.add(makeFrom(newList, n2 - n1));
                ret.add(makeFrom(newList, n1 * n2));
                if (n2 != 0)
                    ret.add(makeFrom(newList, n1 / n2));
                if (n1 != 0)
                    ret.add(makeFrom(newList, n2 / n1));
            }
        }
        return ret;
    }

    static Set<List<Double>> reduce(final Set<List<Double>> set) {
        Set<List<Double>> ret = new HashSet<List<Double>>();
        for (List<Double> l : set)
            ret.addAll(reduce(l));
        return ret;
    }

    static void go() throws Exception {
        int[] nums = new int[] { 1, 2, 3, 4 };
        double eps = 1e-9;
        int maxConsec = 0;
        do {
            List<Double> list = new ArrayList<Double>();
            for (int i : nums)
                list.add((double) i);
            Set<List<Double>> bigSet = reduce(list); // size 3
            bigSet = reduce(bigSet); // size 2
            bigSet = reduce(bigSet); // size 1

            Set<Integer> finalList = new TreeSet<Integer>();
            for (List<Double> l : bigSet) {
                double x = abs(l.get(0));
                int xint = (int) round(x);
                if (abs(xint - x) < eps)
                    finalList.add(xint);
            }

            int i = 0;
            while (finalList.contains(++i))
                ;
            if (i - 1 > maxConsec) {
                maxConsec = i - 1;
                System.out.println(Arrays.toString(nums) + " " + maxConsec);
            }
        } while (next(nums, 3));
    }

    public static void main(final String[] args) throws Exception {
        go();
    }
}
