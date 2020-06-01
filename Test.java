import java.util.Scanner;

public class Test {
    private int getSeparatorIndex(int[] nums, int low, int high) {
        int k = nums[low];
        while (low < high) {
            while (k < nums[high] && low < high) {
                high--;
            }
            if (low < high)
                nums[low++] = nums[high];
            while (k > nums[low] && low < high) {
                low++;
            }
            if (low < high)
                nums[high--] = nums[low];
        }
        nums[low] = k;
        return low;
    }

    public void sort(int num[], int low, int high) {
        if (low < high) {
            int sepIndex = getSeparatorIndex(num, low, high);
            sort(num, low, sepIndex - 1);
            sort(num, sepIndex + 1, high);
        }
    }

    public int findKthMax(int arr[], int k, int left, int right) {
        int sepIndex = getSeparatorIndex(arr, left, right);
        if (k == arr.length - sepIndex) {
            return arr[sepIndex];
        } else {
            if (k > arr.length - sepIndex) {
                return findKthMax(arr, k, left, sepIndex - 1);
            } else {
                return findKthMax(arr, k, sepIndex + 1, right);
            }
        }
    }

    public static void main2(String[] args) {

        Scanner mScanner = new Scanner(System.in);
        String str1 = mScanner.next();
        String str2 = mScanner.next();
        int count = 0;
        int len = str1.length();
        StringBuilder sBuilder=new StringBuilder();
        for (int i = 0; i < len + 1; i++) {
            sBuilder.delete(0, sBuilder.length());
            sBuilder.append(str1.substring(0, i)).append(str2).append(str1.substring(i)) ;
            if (checkHuiWen(sBuilder.toString())) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean checkHuiWen(String str) {
        boolean flag = true;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) != str.charAt(len - 1 - i)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    //输入一个字符串，求出该字符串包含的字符集合
    //每组数据一行，按字符串原有的字符顺序，输出字符集合，即重复出现并靠后的字母不输出
    public static void main1(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char temp = str.charAt(i);
                if (!builder.toString().contains(temp + "")) {
                    builder.append(temp);
                }
            }
            System.out.println(builder.toString());
        }
    }

    class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //合并两个排序的链表
    public ListNode Merge(ListNode list1, ListNode list2) {

        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode mergeHead = null;
        ListNode current = null;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                if (mergeHead == null) {
                    mergeHead = current = list1;
                } else {
                    current.next = list1;
                    current = current.next;
                }
                list1 = list1.next;
            } else {
                if (mergeHead == null) {
                    mergeHead = current = list2;
                } else {
                    current.next = list2;
                    current = current.next;
                }
                list2 = list2.next;
            }

            if (list1 == null) {
                current.next = list2;
            } else {
                current.next = list1;
            }
        }
        return mergeHead;
    }
}
