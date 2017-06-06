
class RangeExtractor {
    public static int addBuilderEntry(int[] arr, StringBuilder builder, int leftPos, int rightPos) {
        int leftVal = arr[leftPos];
        int rightVal = arr[rightPos];
        int prevRightVal = arr[rightPos - 1];
        if ( (Math.abs(rightVal - prevRightVal) != 1) || (rightPos == arr.length - 1)) {
            if (Math.abs(rightPos - leftPos) == 1) {
                builder.append(leftVal);
                if (rightPos != arr.length - 1)
                    builder.append(',');
                leftPos = rightPos;
            } else {
                builder.append(leftVal);
                builder.append('-');
                builder.append(prevRightVal);
                builder.append(',');
                leftPos = rightPos;
            }
        }
        return leftPos;
    }
    
    public static String rangeExtraction(int[] arr) {
        StringBuilder rtn = new StringBuilder();
        int leftPos = 0;
        int rightPos = 1;
        int prevRightVal = arr[leftPos];
        while (rightPos < arr.length) {
            leftPos = addBuilderEntry(arr, rtn, leftPos, rightPos);
            rightPos++;
        }
        return rtn.toString();
    }
}