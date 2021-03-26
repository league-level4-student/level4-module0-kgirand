//https://www.codewars.com/kata/the-wrong-way-cow
//
//Task
//Given a field of cows find which one is the Wrong-Way Cow and return her position.
//
//Notes:
//
//There are always at least 3 cows in a herd
//There is only 1 Wrong-Way Cow!
//Fields are rectangular
//The cow position is zero-based [x,y] of her head (i.e. the letter c)
//Examples
//Ex1
//
//cow.cow.cow.cow.cow
//cow.cow.cow.cow.cow
//cow.woc.cow.cow.cow
//cow.cow.cow.cow.cow
//Answer: [6,2]
//
//Ex2
//
//c..........
//o...c......
//w...o.c....
//....w.o....
//......w.cow
//Answer: [8,4]
//
//Notes
//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
//
//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
//
//...w...
//..cow..
//.woco..
//.ow.c..
//.c.....

package extras.the_wrong_way_cow;

public class TheWrongWayCow {

	public static int[] findWrongWayCow(final char[][] field) {
		// Fill in the code to return the x,y coordinate position of the
		// head (letter 'c') of the wrong way cow!
		char c = 'c';
		char o = 'o';
		char w = 'w';
		int north = 0;
		int[] norths = new int[2];
		int south = 0;
		int[] souths = new int[2];
		int west = 0;
		int[] wests = new int[2];
		int east = 0;
		int[] easts = new int[2];
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (field[i][j] == c) {
					if (field[i].length > j + 2 && field[i][j + 1] == o) {
						if (field[i][j + 2] == w) {
							east += 1;
							easts[0] = i;
							easts[1] = j;
						}
					} else if (j > 1 && field[i][j - 1] == o) {
						if (field[i][j - 2] == w) {
							west += 1;
							wests[0] = i;
							wests[1] = j;
						}
					} else if (field.length > i + 2 && field[i + 1][j] == o) {
						if (field[i + 2][j] == w) {
							north += 1;
							norths[0] = i;
							norths[1] = j;
						}
					} else if (i > 1 && field[i - 1][j] == o) {
						if (field[i - 2][j] == w) {
							south += 1;
							souths[0] = i;
							souths[1] = j;
						}
					}
				}
			}
		}
		System.out.println(north);
		System.out.println(east);
		System.out.println(south);
		System.out.println(west);

		int[] ints = new int[0];
		if (north == 1) {
			ints = norths;
		} else if (south == 1) {
			ints = souths;
		} else if (west == 1) {
			ints = wests;
		} else {
			ints = easts;
		}

		return ints;
	}
}
