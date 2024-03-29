import java.util.*;

public abstract class AbstractSort {

    abstract void sort(Comparable[] arr);

    public static boolean test(AbstractSort as) {
        // generated random integers, using AbstractSort#randomIntegers
        Integer[] arr = preGeneratedIntegers();

        // Plot.plot(arr, false);

        System.out.printf(">>> Testing: %s\n", as.getClass().getName());
        System.out.println(">>> Before Testing:");
        show(arr);

        long start = System.currentTimeMillis();
        as.sort(arr);
        long end = System.currentTimeMillis();

        // Plot.plot(arr, true);

        System.out.println("\n>>> After Testing:");
        show(arr);

        System.out.printf("\n>>> Took: %d miliseconds, Sample size: %d\n", end - start, arr.length);
        if (!isSorted(arr)) {
            System.out.println(">>> Not Sorted!!!");
            return false;
        } else {
            System.out.println(">>> Sorted!!!");
            return true;
        }
    }

    /**
     * Return whether v is less than w
     */
    protected boolean lessThan(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    protected void swap(Comparable[] a, int i, int j) {
        if (i == j)
            return;
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    protected static void show(Comparable[] a) {
        System.out.println(Arrays.toString(a));
    }

    /**
     * Return whether the arr is in ascending order
     */
    public static boolean isSorted(Comparable[] a) {
//        for (int i = 1; i < a.length; i++) {
//            if (lessThan(a[i], a[i - 1]))
//                return false;
//        }
//        return true;
        Comparable[] p = preGeneratedIntegers();
        Arrays.sort(p);
        return Arrays.equals(a, p);
    }

    private static Integer[] randomIntegers(final int N) {
        Integer arr[] = new Integer[N];
        Random r = new Random();
        for (int i = 0; i < N; i++) {
            arr[i] = r.nextInt(N);
        }
        return arr;
    }

    private static Integer[] preGeneratedIntegers() {
        return new Integer[]{730, 594, 805, 50, 762, 16, 642, 929, 998, 954, 628, 813, 871, 243, 218, 655, 352, 804,
                590, 969, 871, 36, 476, 380, 762, 115, 220, 516, 866, 924, 322, 504, 761, 49, 382, 936, 972, 816, 733,
                744, 781, 314, 92, 465, 439, 156, 187, 167, 982, 367, 526, 237, 147, 355, 755, 307, 834, 952, 673, 18,
                744, 52, 110, 910, 316, 457, 154, 954, 560, 990, 501, 122, 548, 173, 570, 721, 943, 725, 103, 483, 963,
                142, 122, 309, 204, 97, 649, 497, 285, 594, 870, 453, 999, 942, 829, 315, 670, 63, 196, 979, 195, 150,
                374, 276, 255, 87, 315, 889, 683, 880, 264, 816, 89, 332, 238, 199, 510, 292, 282, 855, 551, 277, 800,
                923, 60, 317, 837, 249, 13, 974, 736, 814, 16, 69, 419, 721, 644, 796, 956, 301, 966, 163, 643, 738, 93,
                246, 95, 558, 924, 816, 161, 732, 203, 517, 740, 870, 322, 372, 800, 775, 724, 28, 882, 841, 859, 46,
                217, 773, 854, 544, 576, 554, 676, 417, 963, 516, 859, 600, 383, 453, 218, 879, 523, 762, 271, 429, 181,
                188, 414, 842, 954, 607, 230, 171, 230, 743, 409, 605, 308, 909, 785, 334, 939, 394, 34, 439, 285, 843,
                663, 845, 300, 562, 717, 319, 219, 197, 382, 714, 407, 176, 267, 792, 649, 477, 377, 840, 233, 788, 494,
                70, 39, 757, 248, 563, 233, 98, 795, 735, 268, 930, 133, 179, 529, 48, 360, 987, 807, 370, 611, 408,
                614, 69, 597, 988, 663, 198, 719, 686, 445, 355, 365, 878, 879, 656, 849, 235, 846, 366, 531, 591, 702,
                646, 921, 908, 850, 27, 887, 312, 176, 101, 508, 707, 694, 777, 560, 633, 760, 550, 573, 651, 944, 258,
                954, 840, 648, 851, 726, 553, 310, 354, 575, 298, 541, 370, 871, 741, 772, 663, 799, 312, 93, 481, 59,
                854, 548, 999, 506, 732, 370, 632, 568, 849, 565, 548, 873, 480, 453, 489, 788, 915, 686, 819, 634, 45,
                29, 595, 602, 209, 825, 199, 435, 9, 328, 546, 949, 455, 299, 274, 844, 367, 902, 392, 722, 989, 960,
                45, 855, 99, 787, 311, 216, 699, 465, 155, 665, 507, 980, 132, 704, 54, 903, 921, 832, 6, 998, 845, 406,
                848, 630, 640, 500, 712, 147, 859, 680, 659, 125, 425, 64, 556, 494, 553, 932, 278, 154, 551, 415, 856,
                371, 178, 819, 814, 777, 48, 697, 284, 442, 726, 522, 966, 491, 508, 589, 602, 835, 253, 46, 199, 504,
                140, 198, 359, 489, 185, 869, 528, 735, 571, 689, 232, 169, 280, 809, 382, 603, 912, 162, 574, 657, 224,
                445, 436, 453, 42, 436, 730, 563, 505, 943, 980, 270, 250, 992, 545, 649, 558, 438, 36, 556, 260, 874,
                760, 808, 515, 452, 930, 201, 125, 952, 368, 735, 136, 763, 280, 793, 574, 652, 683, 607, 252, 849, 88,
                602, 383, 327, 916, 154, 778, 149, 39, 691, 844, 78, 787, 605, 159, 521, 982, 135, 563, 87, 438, 688,
                761, 576, 556, 689, 404, 314, 561, 962, 345, 535, 997, 663, 959, 401, 950, 407, 17, 77, 589, 689, 329,
                754, 485, 724, 975, 452, 565, 105, 158, 889, 469, 279, 56, 167, 845, 35, 400, 201, 664, 169, 373, 988,
                247, 635, 782, 618, 723, 541, 632, 890, 394, 947, 853, 761, 394, 120, 703, 33, 916, 956, 43, 644, 960,
                408, 104, 86, 30, 953, 166, 781, 573, 711, 107, 558, 636, 625, 472, 524, 373, 368, 315, 615, 170, 948,
                294, 271, 495, 750, 700, 228, 151, 419, 204, 92, 299, 745, 534, 103, 621, 801, 882, 555, 499, 258, 312,
                551, 878, 342, 985, 374, 58, 211, 413, 18, 920, 600, 574, 169, 223, 993, 482, 241, 194, 697, 606, 332,
                531, 796, 661, 824, 724, 414, 248, 254, 126, 363, 423, 524, 202, 52, 790, 425, 45, 371, 874, 717, 362,
                675, 515, 587, 663, 158, 940, 307, 80, 381, 653, 272, 274, 342, 634, 467, 96, 485, 44, 586, 314, 185,
                645, 265, 172, 249, 777, 635, 339, 339, 472, 973, 921, 457, 207, 47, 681, 33, 831, 912, 407, 192, 525,
                373, 766, 770, 248, 653, 13, 536, 962, 948, 420, 782, 157, 226, 920, 544, 479, 976, 278, 536, 240, 806,
                12, 735, 760, 468, 835, 630, 102, 899, 505, 367, 563, 286, 163, 789, 708, 66, 455, 788, 294, 32, 591,
                895, 852, 299, 722, 733, 724, 195, 316, 446, 200, 668, 158, 999, 977, 691, 32, 624, 605, 7, 579, 575,
                465, 904, 19, 619, 170, 710, 465, 862, 484, 176, 847, 719, 182, 280, 389, 388, 541, 264, 584, 329, 940,
                787, 522, 751, 109, 882, 149, 580, 666, 841, 992, 866, 540, 894, 108, 911, 765, 453, 121, 665, 705, 363,
                265, 579, 718, 493, 430, 449, 204, 691, 359, 628, 831, 79, 293, 194, 82, 580, 812, 333, 310, 21, 390,
                302, 825, 532, 801, 278, 985, 219, 81, 881, 831, 202, 536, 823, 584, 286, 305, 568, 478, 858, 300, 12,
                574, 140, 987, 204, 659, 486, 547, 696, 952, 824, 207, 478, 842, 895, 455, 308, 211, 892, 749, 290, 147,
                387, 264, 855, 65, 742, 633, 102, 816, 694, 771, 404, 149, 622, 736, 873, 916, 91, 566, 433, 321, 188,
                256, 614, 996, 234, 825, 60, 471, 92, 555, 688, 857, 306, 117, 38, 213, 379, 148, 629, 932, 685, 528,
                593, 854, 464, 311, 86, 304, 152, 439, 739, 264, 974, 333, 306, 492, 65, 891, 715, 400, 958, 301, 176,
                454, 537, 308, 552, 39, 943, 300, 699, 640, 970, 952, 591, 520, 32, 965, 21, 189, 952, 548, 923, 431,
                937, 913, 79, 236, 89, 897, 868, 915, 456, 936, 268, 820, 575, 96, 139, 708, 383, 318, 395, 926, 66,
                238, 162, 333, 344, 174, 228, 862, 111, 688, 309, 186, 547, 986, 543, 146, 822, 207, 389, 899, 628, 800,
                618, 320, 237, 509, 481, 71, 256, 917, 610, 124, 448, 542, 861, 545};
    }
}