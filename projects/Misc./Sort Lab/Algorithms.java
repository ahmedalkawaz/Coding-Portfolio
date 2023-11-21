
import java.util.Arrays;

public class Algorithms {

	public static void main(String[] args) {
		int[] arr = new int[50];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 100);
		}
		System.out.println(Arrays.toString(arr));
		bubbleSort(arr);
		System.out.println(Arrays.toString(arr));

	}

	public static int binarySearch(int[] arr, int e) {
		int start = 0;
		int end = arr.length - 1;
		int loc = arr.length / 2;
		while (start <= end) {
			if (arr[loc] == e) {
				return loc;
			}
			if (arr[loc] < e) {
				start = loc + 1;
			} else {
				end = loc - 1;
				loc = (start + end) / 2;
			}
			loc = -1;
		}
		return loc;
	}

	public static int binarySearch(Comparable[] arr, Comparable e) {
		int start = 0;
		int end = arr.length - 1;
		int loc = arr.length / 2;
		while (start <= end) {
			if (arr[loc].equals(e)) {
				return loc;
			}
			if (arr[loc].compareTo(e) < 0) {
				start = loc + 1;
			} else {
				end = loc - 1;
				loc = (start + end) / 2;
			}
			loc = -1;
		}
		return loc;
	}

	public static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int minLoc = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[minLoc]) {
					minLoc = j;
				}
			}
			swap(arr, minLoc, i);
		}
	}

	public static void bubbleSort(int[] arr) {
		boolean hasSwaps;
		int count = 0;
		do {
			hasSwaps = false;
			for(int i = 0;i<arr.length-count-1;i++) {
				if(arr[i]>arr[i+1]) {
					swap(arr,i,i+1);
					hasSwaps = true;
					}
			}
			count++;
			
		}while(hasSwaps);

	}

	public static void swap(int[] arr, int x, int y) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}

}
