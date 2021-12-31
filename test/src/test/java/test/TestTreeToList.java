package test;

import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yangkai
 * @date 2021/8/17
 * @email yangkai@hujiang.com
 * @description
 */
public class TestTreeToList {

    private TreeNode buildTreeNode1() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);
        TreeNode n10 = new TreeNode(10);

        TreeNode n11 = new TreeNode(11);
        TreeNode n12 = new TreeNode(12);
        TreeNode n13 = new TreeNode(13);
        TreeNode n14 = new TreeNode(14);
        TreeNode n15 = new TreeNode(15);
        TreeNode n16 = new TreeNode(16);
        TreeNode n17 = new TreeNode(17);
        TreeNode n18 = new TreeNode(18);
        TreeNode n19 = new TreeNode(19);
        TreeNode n20 = new TreeNode(20);

        TreeNode n21 = new TreeNode(21);
        TreeNode n22 = new TreeNode(22);
        TreeNode n23 = new TreeNode(23);
        TreeNode n24 = new TreeNode(24);
        TreeNode n25 = new TreeNode(25);
        TreeNode n26 = new TreeNode(26);
        TreeNode n27 = new TreeNode(27);
        TreeNode n28 = new TreeNode(28);
        TreeNode n29 = new TreeNode(29);
        TreeNode n30 = new TreeNode(30);

        TreeNode n31 = new TreeNode(31);
        TreeNode n32 = new TreeNode(32);
        TreeNode n33 = new TreeNode(33);

        n1.setParent(null);
        n1.setChildren(Lists.newArrayList(n2, n3, n4));

        n2.setParent(n1);
        n2.setChildren(Lists.newArrayList(n5, n6, n7));
        n3.setParent(n1);
        n3.setChildren(Lists.newArrayList(n8, n9, n10));
        n4.setParent(n1);
        n4.setChildren(Lists.newArrayList(n11, n12, n13));

        n5.setParent(n2);
        n5.setChildren(Lists.newArrayList(n14, n15));
        n6.setParent(n2);
        n6.setChildren(Lists.newArrayList(n16, n17));
        n7.setParent(n2);
        n7.setChildren(Lists.newArrayList(n18, n19));

        n8.setParent(n3);
        n8.setChildren(Lists.newArrayList(n20, n21));
        n9.setParent(n3);
        n9.setChildren(Lists.newArrayList(n22, n23));
        n10.setParent(n3);
        n10.setChildren(Lists.newArrayList(n24, n25));

        n11.setParent(n4);
        n11.setChildren(Lists.newArrayList(n26, n27));
        n12.setParent(n4);
        n12.setChildren(Lists.newArrayList(n28, n29));
        n13.setParent(n4);
        n13.setChildren(Lists.newArrayList(n30, n31));

        n14.setParent(n5);
        n14.setChildren(Lists.newArrayList(n32, n33));

        n15.setParent(n5);
        n15.setChildren(null);

        n16.setParent(n6);
        n16.setChildren(null);

        n17.setParent(n6);
        n17.setChildren(null);

        n18.setParent(n7);
        n18.setChildren(null);

        n19.setParent(n7);
        n19.setChildren(null);

        n20.setParent(n8);
        n20.setChildren(null);

        n21.setParent(n8);
        n21.setChildren(null);

        n22.setParent(n9);
        n22.setChildren(null);

        n23.setParent(n9);
        n23.setChildren(null);

        n24.setParent(n10);
        n24.setChildren(null);

        n25.setParent(n10);
        n25.setChildren(null);

        n26.setParent(n11);
        n26.setChildren(null);

        n27.setParent(n11);
        n27.setChildren(null);

        n28.setParent(n12);
        n28.setChildren(null);

        n29.setParent(n12);
        n29.setChildren(null);

        n30.setParent(n13);
        n30.setChildren(null);

        n31.setParent(n13);
        n31.setChildren(null);

        n32.setParent(n14);
        n32.setChildren(null);

        n33.setParent(n14);
        n33.setChildren(null);
        return n1;
    }

    private TreeNode buildTreeNode2() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);
        TreeNode n10 = new TreeNode(10);

        TreeNode n11 = new TreeNode(11);
        TreeNode n12 = new TreeNode(12);
        TreeNode n13 = new TreeNode(13);
        TreeNode n14 = new TreeNode(14);
        TreeNode n15 = new TreeNode(15);
        TreeNode n16 = new TreeNode(16);
        TreeNode n17 = new TreeNode(17);
        TreeNode n18 = new TreeNode(18);
        TreeNode n19 = new TreeNode(19);
        TreeNode n20 = new TreeNode(20);

        TreeNode n21 = new TreeNode(21);
        TreeNode n22 = new TreeNode(22);
        TreeNode n23 = new TreeNode(23);
        TreeNode n24 = new TreeNode(24);
        TreeNode n25 = new TreeNode(25);
        TreeNode n26 = new TreeNode(26);
        TreeNode n27 = new TreeNode(27);
        TreeNode n28 = new TreeNode(28);
        TreeNode n29 = new TreeNode(29);
        TreeNode n30 = new TreeNode(30);

        TreeNode n31 = new TreeNode(31);
        TreeNode n32 = new TreeNode(32);
        TreeNode n33 = new TreeNode(33);

        TreeNode n34 = new TreeNode(34);
        TreeNode n35 = new TreeNode(35);
        TreeNode n36 = new TreeNode(36);

        n1.setParent(null);
        n1.setChildren(Lists.newArrayList(n2, n3, n4));

        n2.setParent(n1);
        n2.setChildren(Lists.newArrayList(n5, n6, n7));
        n3.setParent(n1);
        n3.setChildren(Lists.newArrayList(n8, n9, n10));
        n4.setParent(n1);
        n4.setChildren(Lists.newArrayList(n11, n12, n13));

        n5.setParent(n2);
        n5.setChildren(Lists.newArrayList(n14, n15));
        n6.setParent(n2);
        n6.setChildren(Lists.newArrayList(n16, n17));
        n7.setParent(n2);
        n7.setChildren(Lists.newArrayList(n18, n19));

        n8.setParent(n3);
        n8.setChildren(Lists.newArrayList(n20, n21));
        n9.setParent(n3);
        n9.setChildren(Lists.newArrayList(n22, n23));
        n10.setParent(n3);
        n10.setChildren(Lists.newArrayList(n24, n25));

        n11.setParent(n4);
        n11.setChildren(Lists.newArrayList(n26, n27));
        n12.setParent(n4);
        n12.setChildren(Lists.newArrayList(n28, n29));
        n13.setParent(n4);
        n13.setChildren(Lists.newArrayList(n30, n31));

        n14.setParent(n5);
        n14.setChildren(Lists.newArrayList(n32, n33));

        n15.setParent(n5);
        n15.setChildren(null);

        n16.setParent(n6);
        n16.setChildren(null);

        n17.setParent(n6);
        n17.setChildren(null);

        n18.setParent(n7);
        n18.setChildren(null);

        n19.setParent(n7);
        n19.setChildren(null);

        n20.setParent(n8);
        n20.setChildren(null);

        n21.setParent(n8);
        n21.setChildren(null);

        n22.setParent(n9);
        n22.setChildren(null);

        n23.setParent(n9);
        n23.setChildren(null);

        n24.setParent(n10);
        n24.setChildren(null);

        n25.setParent(n10);
        n25.setChildren(null);

        n26.setParent(n11);
        n26.setChildren(null);

        n27.setParent(n11);
        n27.setChildren(null);

        n28.setParent(n12);
        n28.setChildren(null);

        n29.setParent(n12);
        n29.setChildren(null);

        n30.setParent(n13);
        n30.setChildren(Lists.newArrayList(n34,n35,n36));

        n31.setParent(n13);
        n31.setChildren(null);

        n32.setParent(n14);
        n32.setChildren(null);

        n33.setParent(n14);
        n33.setChildren(null);

        n34.setParent(n30);
        n34.setChildren(null);

        n35.setParent(n30);
        n35.setChildren(null);

        n36.setParent(n30);
        n36.setChildren(null);

        return n1;
    }

    @Test
    public void test_tree_to_list() {
        TreeNode n1 = buildTreeNode2();
        List<List<TreeNode>> lists = n1.treeToList();
        for (List<TreeNode> list : lists) {
            System.out.println(list.stream().map(e -> e.getValue()).collect(Collectors.toList()));
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TreeNode {
        private TreeNode parent;
        private List<TreeNode> children;
        private Integer value;

        public TreeNode(Integer value) {
            this.value = value;
        }

        public List<List<TreeNode>> treeToList() {
            List<List<TreeNode>> all = Lists.newArrayList();
            List<TreeNode> list = Lists.newArrayList();

            this.treeToList(all, list);

            return all;
        }

        private void treeToList(List<List<TreeNode>> all, List<TreeNode> list) {
            if (CollectionUtils.isNotEmpty(this.getChildren())) {
                List<TreeNode> children = this.getChildren();
                List<List<TreeNode>> x = Lists.newArrayList();
                for (int i = 0; i < children.size(); i++) {
                    List<TreeNode> c = Lists.newArrayList();
                    if (CollectionUtils.isNotEmpty(list)) {
                        c.addAll(list);
                    }

                    c.add(this);
                    x.add(c);
                }

                for (int i = 0; i < children.size(); i++) {
                    TreeNode c = children.get(i);
                    c.treeToList(all, x.get(i));
                }

            } else {
                //叶子节点
                List<TreeNode> c = Lists.newArrayList();
                c.addAll(list);
                c.add(this);
                all.add(c);
            }
        }
    }
}
