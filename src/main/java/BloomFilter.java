public class BloomFilter
{
    public int filter_len;

    public int bitArray = 0;

    public BloomFilter(int f_len)
    {
        filter_len = f_len;
        // создаём битовый массив длиной f_len ...
    }

    // хэш-функции
    public int hash1(String str1)
    {
        // 17
        int result = 0;
        for(int i=0; i<str1.length(); i++)
        {
            int code = (int)str1.charAt(i);
            result = result * 17 + code;
            result = Math.abs(result % filter_len);
        }
        return result;
    }
    public int hash2(String str1)
    {
        // 223
        int result = 0;
        for(int i=0; i<str1.length(); i++)
        {
            int code = (int)str1.charAt(i);
            result = result * 223 + code;
            result = Math.abs(result % filter_len);
        }
        return result;
    }

    public void add(String str1)
    {
        int pow1 = hash1(str1);
        int pow2 = hash2(str1);

        int mask1 = (int)Math.pow(2, pow1);
        int mask2 = (int)Math.pow(2, pow2);

        bitArray = bitArray | mask1;
        bitArray = bitArray | mask2;
    }

    public boolean isValue(String str1)
    {
        int pow1 = hash1(str1);
        int pow2 = hash2(str1);

        int mask1 = (int)Math.pow(2, pow1);
        int mask2 = (int)Math.pow(2, pow2);

        int res1 = bitArray & mask1;
        int res2 = bitArray & mask2;

        return mask1 == res1 && mask2 == res2;
    }
}