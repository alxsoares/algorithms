package alex.algorithms.arrays;

public class MoreThanNdK {

	public static void moreThanNdK(int a[], int k) {
		if (k < 2)
			return;
		ElementCount temp[] = new ElementCount[k - 1];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = new ElementCount();
		}
		for (int i = 0; i < a.length; i++) {
			int j = 0;
			for (j = 0; j < k - 1; j++) {//found, the increase
				if (temp[j].element == a[i]) {
					temp[j].count++;
					break;
				}
			}
			if (j == k - 1) {// not found in temp
				int l;
				for (l = 0; l < k - 1; l++) {
					if (temp[l].count == 0) {
						temp[l].element = a[i];
						temp[l].count = 1;
						break;
					}
				}
				if (l == k - 1) {// decrease all counts
					for (l = 0; l < k - 1; l++) {
						temp[l].count--;
					}
				}
			}
		}
		for (int i = 0; i < k - 1; i++) {
			int count = 0;
			for (int j = 0; j < a.length; j++) {
				if (temp[i].element == a[j]) {
					count++;
				}
			}
			if (count > a.length / k) {
				System.out.printf("Number %d has count %d\n", temp[i].element,
						count);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("Test 1");
	    int arr1[] = {4, 5, 6, 7, 8, 4, 4};
	    int k = 3;
	    moreThanNdK(arr1, k);
	    System.out.println("Test 2");
	    int arr2[] = {4, 2, 2, 7};
	    k = 3;
	    moreThanNdK(arr2,  k);
	    System.out.println("Test 3");
	    int arr3[] = {2, 7, 2};
	    k = 2;
	    moreThanNdK(arr3,  k);
	    System.out.println("Test 4");
	    int arr4[] = {2, 3, 3, 2};
	    k = 3;
	    moreThanNdK(arr4, k);
	}

}

class ElementCount {
	int element;
	int count;

}
