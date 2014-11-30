//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java.lang;

import java.io.ObjectStreamField;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class String implements Serializable, Comparable<String>, CharSequence {
    private final char[] value;
    private final int offset;
    private final int count;
    private int hash;
    private static final long serialVersionUID = -6849794470754667710L;
    private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[0];
    public static final Comparator<String> CASE_INSENSITIVE_ORDER = new String.CaseInsensitiveComparator();

    public String() {
        this.offset = 0;
        this.count = 0;
        this.value = new char[0];
    }

    public String(String var1) {
        int var2 = var1.count;
        char[] var3 = var1.value;
        char[] var4;
        if(var3.length > var2) {
            int var5 = var1.offset;
            var4 = Arrays.copyOfRange(var3, var5, var5 + var2);
        } else {
            var4 = var3;
        }

        this.offset = 0;
        this.count = var2;
        this.value = var4;
    }

    public String(char[] var1) {
        this.offset = 0;
        this.count = var1.length;
        this.value = StringValue.from(var1);
    }

    public String(char[] var1, int var2, int var3) {
        if(var2 < 0) {
            throw new StringIndexOutOfBoundsException(var2);
        } else if(var3 < 0) {
            throw new StringIndexOutOfBoundsException(var3);
        } else if(var2 > var1.length - var3) {
            throw new StringIndexOutOfBoundsException(var2 + var3);
        } else {
            this.offset = 0;
            this.count = var3;
            this.value = Arrays.copyOfRange(var1, var2, var2 + var3);
        }
    }

    public String(int[] var1, int var2, int var3) {
        if(var2 < 0) {
            throw new StringIndexOutOfBoundsException(var2);
        } else if(var3 < 0) {
            throw new StringIndexOutOfBoundsException(var3);
        } else if(var2 > var1.length - var3) {
            throw new StringIndexOutOfBoundsException(var2 + var3);
        } else {
            int var4 = 0;
            int var5 = 1;
            char[] var6 = new char[var3 + var5];
            int var7 = var2;
            int var8 = 0;

            for(int var9 = 0; var9 < var3; ++var9) {
                int var10 = var1[var7++];
                if(var10 < 0) {
                    throw new IllegalArgumentException();
                }

                if(var5 <= 0 && var8 + 1 >= var6.length) {
                    if(var4 == 0) {
                        var4 = ((-var5 + 1) * var3 << 10) / var9;
                        var4 >>= 10;
                        if(var4 <= 0) {
                            var4 = 1;
                        }
                    } else {
                        var4 *= 2;
                    }

                    int var11 = Math.min(var6.length + var4, var3 * 2);
                    var5 = var11 - var6.length - (var3 - var9);
                    var6 = Arrays.copyOf(var6, var11);
                }

                if(var10 < 65536) {
                    var6[var8++] = (char)var10;
                } else {
                    if(var10 > 1114111) {
                        throw new IllegalArgumentException();
                    }

                    Character.toSurrogates(var10, var6, var8);
                    var8 += 2;
                    --var5;
                }
            }

            this.offset = 0;
            this.value = var6;
            this.count = var8;
        }
    }

    /** @deprecated */
    @Deprecated
    public String(byte[] var1, int var2, int var3, int var4) {
        checkBounds(var1, var3, var4);
        char[] var5 = new char[var4];
        int var6;
        if(var2 == 0) {
            for(var6 = var4; var6-- > 0; var5[var6] = (char)(var1[var6 + var3] & 255)) {
                ;
            }
        } else {
            var2 <<= 8;

            for(var6 = var4; var6-- > 0; var5[var6] = (char)(var2 | var1[var6 + var3] & 255)) {
                ;
            }
        }

        this.offset = 0;
        this.count = var4;
        this.value = var5;
    }

    /** @deprecated */
    @Deprecated
    public String(byte[] var1, int var2) {
        this(var1, var2, 0, var1.length);
    }

    private static void checkBounds(byte[] var0, int var1, int var2) {
        if(var2 < 0) {
            throw new StringIndexOutOfBoundsException(var2);
        } else if(var1 < 0) {
            throw new StringIndexOutOfBoundsException(var1);
        } else if(var1 > var0.length - var2) {
            throw new StringIndexOutOfBoundsException(var1 + var2);
        }
    }

    public String(byte[] var1, int var2, int var3, String var4) throws UnsupportedEncodingException {
        if(var4 == null) {
            throw new NullPointerException("charsetName");
        } else {
            checkBounds(var1, var2, var3);
            char[] var5 = StringCoding.decode(var4, var1, var2, var3);
            this.offset = 0;
            this.count = var5.length;
            this.value = var5;
        }
    }

    public String(byte[] var1, int var2, int var3, Charset var4) {
        if(var4 == null) {
            throw new NullPointerException("charset");
        } else {
            checkBounds(var1, var2, var3);
            char[] var5 = StringCoding.decode(var4, var1, var2, var3);
            this.offset = 0;
            this.count = var5.length;
            this.value = var5;
        }
    }

    public String(byte[] var1, String var2) throws UnsupportedEncodingException {
        this(var1, 0, var1.length, (String)var2);
    }

    public String(byte[] var1, Charset var2) {
        this(var1, 0, var1.length, (Charset)var2);
    }

    public String(byte[] var1, int var2, int var3) {
        checkBounds(var1, var2, var3);
        char[] var4 = StringCoding.decode(var1, var2, var3);
        this.offset = 0;
        this.count = var4.length;
        this.value = var4;
    }

    public String(byte[] var1) {
        this((byte[])var1, 0, var1.length);
    }

    public String(StringBuffer var1) {
        String var2 = var1.toString();
        this.value = var2.value;
        this.count = var2.count;
        this.offset = var2.offset;
    }

    public String(StringBuilder var1) {
        String var2 = var1.toString();
        this.value = var2.value;
        this.count = var2.count;
        this.offset = var2.offset;
    }

    String(int var1, int var2, char[] var3) {
        this.value = var3;
        this.offset = var1;
        this.count = var2;
    }

    public int length() {
        return this.count;
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public char charAt(int var1) {
        if(var1 >= 0 && var1 < this.count) {
            return this.value[var1 + this.offset];
        } else {
            throw new StringIndexOutOfBoundsException(var1);
        }
    }

    public int codePointAt(int var1) {
        if(var1 >= 0 && var1 < this.count) {
            return Character.codePointAtImpl(this.value, this.offset + var1, this.offset + this.count);
        } else {
            throw new StringIndexOutOfBoundsException(var1);
        }
    }

    public int codePointBefore(int var1) {
        int var2 = var1 - 1;
        if(var2 >= 0 && var2 < this.count) {
            return Character.codePointBeforeImpl(this.value, this.offset + var1, this.offset);
        } else {
            throw new StringIndexOutOfBoundsException(var1);
        }
    }

    public int codePointCount(int var1, int var2) {
        if(var1 >= 0 && var2 <= this.count && var1 <= var2) {
            return Character.codePointCountImpl(this.value, this.offset + var1, var2 - var1);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int offsetByCodePoints(int var1, int var2) {
        if(var1 >= 0 && var1 <= this.count) {
            return Character.offsetByCodePointsImpl(this.value, this.offset, this.count, this.offset + var1, var2) - this.offset;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    void getChars(char[] var1, int var2) {
        System.arraycopy(this.value, this.offset, var1, var2, this.count);
    }

    public void getChars(int var1, int var2, char[] var3, int var4) {
        if(var1 < 0) {
            throw new StringIndexOutOfBoundsException(var1);
        } else if(var2 > this.count) {
            throw new StringIndexOutOfBoundsException(var2);
        } else if(var1 > var2) {
            throw new StringIndexOutOfBoundsException(var2 - var1);
        } else {
            System.arraycopy(this.value, this.offset + var1, var3, var4, var2 - var1);
        }
    }

    /** @deprecated */
    @Deprecated
    public void getBytes(int var1, int var2, byte[] var3, int var4) {
        if(var1 < 0) {
            throw new StringIndexOutOfBoundsException(var1);
        } else if(var2 > this.count) {
            throw new StringIndexOutOfBoundsException(var2);
        } else if(var1 > var2) {
            throw new StringIndexOutOfBoundsException(var2 - var1);
        } else {
            int var5 = var4;
            int var6 = this.offset + var2;
            int var7 = this.offset + var1;

            for(char[] var8 = this.value; var7 < var6; var3[var5++] = (byte)var8[var7++]) {
                ;
            }

        }
    }

    public byte[] getBytes(String var1) throws UnsupportedEncodingException {
        if(var1 == null) {
            throw new NullPointerException();
        } else {
            return StringCoding.encode(var1, this.value, this.offset, this.count);
        }
    }

    public byte[] getBytes(Charset var1) {
        if(var1 == null) {
            throw new NullPointerException();
        } else {
            return StringCoding.encode(var1, this.value, this.offset, this.count);
        }
    }

    public byte[] getBytes() {
        return StringCoding.encode(this.value, this.offset, this.count);
    }

    public boolean equals(Object var1) {
        if(this == var1) {
            return true;
        } else {
            if(var1 instanceof String) {
                String var2 = (String)var1;
                int var3 = this.count;
                if(var3 == var2.count) {
                    char[] var4 = this.value;
                    char[] var5 = var2.value;
                    int var6 = this.offset;
                    int var7 = var2.offset;

                    do {
                        if(var3-- == 0) {
                            return true;
                        }
                    } while(var4[var6++] == var5[var7++]);

                    return false;
                }
            }

            return false;
        }
    }

    public boolean contentEquals(StringBuffer var1) {
        synchronized(var1) {
            return this.contentEquals((CharSequence)var1);
        }
    }

    public boolean contentEquals(CharSequence var1) {
        if(this.count != var1.length()) {
            return false;
        } else {
            char[] var2;
            int var4;
            int var5;
            if(var1 instanceof AbstractStringBuilder) {
                var2 = this.value;
                char[] var3 = ((AbstractStringBuilder)var1).getValue();
                var4 = this.offset;
                var5 = 0;
                int var6 = this.count;

                while(var6-- != 0) {
                    if(var2[var4++] != var3[var5++]) {
                        return false;
                    }
                }
            }

            if(var1.equals(this)) {
                return true;
            } else {
                var2 = this.value;
                int var7 = this.offset;
                var4 = 0;
                var5 = this.count;

                do {
                    if(var5-- == 0) {
                        return true;
                    }
                } while(var2[var7++] == var1.charAt(var4++));

                return false;
            }
        }
    }

    public boolean equalsIgnoreCase(String var1) {
        return this == var1?true:var1 != null && var1.count == this.count && this.regionMatches(true, 0, var1, 0, this.count);
    }

    public int compareTo(String var1) {
        int var2 = this.count;
        int var3 = var1.count;
        int var4 = Math.min(var2, var3);
        char[] var5 = this.value;
        char[] var6 = var1.value;
        int var7 = this.offset;
        int var8 = var1.offset;
        if(var7 == var8) {
            int var9 = var7;

            for(int var10 = var4 + var7; var9 < var10; ++var9) {
                char var11 = var5[var9];
                char var12 = var6[var9];
                if(var11 != var12) {
                    return var11 - var12;
                }
            }
        } else {
            while(var4-- != 0) {
                char var14 = var5[var7++];
                char var13 = var6[var8++];
                if(var14 != var13) {
                    return var14 - var13;
                }
            }
        }

        return var2 - var3;
    }

    public int compareToIgnoreCase(String var1) {
        return CASE_INSENSITIVE_ORDER.compare(this, var1);
    }

    public boolean regionMatches(int var1, String var2, int var3, int var4) {
        char[] var5 = this.value;
        int var6 = this.offset + var1;
        char[] var7 = var2.value;
        int var8 = var2.offset + var3;
        if(var3 >= 0 && var1 >= 0 && (long)var1 <= (long)this.count - (long)var4 && (long)var3 <= (long)var2.count - (long)var4) {
            do {
                if(var4-- <= 0) {
                    return true;
                }
            } while(var5[var6++] == var7[var8++]);

            return false;
        } else {
            return false;
        }
    }

    public boolean regionMatches(boolean var1, int var2, String var3, int var4, int var5) {
        char[] var6 = this.value;
        int var7 = this.offset + var2;
        char[] var8 = var3.value;
        int var9 = var3.offset + var4;
        if(var4 >= 0 && var2 >= 0 && (long)var2 <= (long)this.count - (long)var5 && (long)var4 <= (long)var3.count - (long)var5) {
            char var12;
            char var13;
            do {
                char var10;
                char var11;
                do {
                    if(var5-- <= 0) {
                        return true;
                    }

                    var10 = var6[var7++];
                    var11 = var8[var9++];
                } while(var10 == var11);

                if(!var1) {
                    break;
                }

                var12 = Character.toUpperCase(var10);
                var13 = Character.toUpperCase(var11);
            } while(var12 == var13 || Character.toLowerCase(var12) == Character.toLowerCase(var13));

            return false;
        } else {
            return false;
        }
    }

    public boolean startsWith(String var1, int var2) {
        char[] var3 = this.value;
        int var4 = this.offset + var2;
        char[] var5 = var1.value;
        int var6 = var1.offset;
        int var7 = var1.count;
        if(var2 >= 0 && var2 <= this.count - var7) {
            do {
                --var7;
                if(var7 < 0) {
                    return true;
                }
            } while(var3[var4++] == var5[var6++]);

            return false;
        } else {
            return false;
        }
    }

    public boolean startsWith(String var1) {
        return this.startsWith(var1, 0);
    }

    public boolean endsWith(String var1) {
        return this.startsWith(var1, this.count - var1.count);
    }

    public int hashCode() {
        int var1 = this.hash;
        int var2 = this.count;
        if(var1 == 0 && var2 > 0) {
            int var3 = this.offset;
            char[] var4 = this.value;

            for(int var5 = 0; var5 < var2; ++var5) {
                var1 = 31 * var1 + var4[var3++];
            }

            this.hash = var1;
        }

        return var1;
    }

    public int indexOf(int var1) {
        return this.indexOf(var1, 0);
    }

    public int indexOf(int var1, int var2) {
        int var3 = this.offset + this.count;
        char[] var4 = this.value;
        if(var2 < 0) {
            var2 = 0;
        } else if(var2 >= this.count) {
            return -1;
        }

        int var5 = this.offset + var2;
        if(var1 < 65536) {
            while(var5 < var3) {
                if(var4[var5] == var1) {
                    return var5 - this.offset;
                }

                ++var5;
            }

            return -1;
        } else {
            if(var1 <= 1114111) {
                for(char[] var6 = Character.toChars(var1); var5 < var3; ++var5) {
                    if(var4[var5] == var6[0]) {
                        if(var5 + 1 == var3) {
                            break;
                        }

                        if(var4[var5 + 1] == var6[1]) {
                            return var5 - this.offset;
                        }
                    }
                }
            }

            return -1;
        }
    }

    public int lastIndexOf(int var1) {
        return this.lastIndexOf(var1, this.count - 1);
    }

    public int lastIndexOf(int var1, int var2) {
        int var3 = this.offset;
        char[] var4 = this.value;
        int var5 = this.offset + (var2 >= this.count?this.count - 1:var2);
        if(var1 < 65536) {
            while(var5 >= var3) {
                if(var4[var5] == var1) {
                    return var5 - this.offset;
                }

                --var5;
            }

            return -1;
        } else {
            int var6 = this.offset + this.count;
            if(var1 <= 1114111) {
                for(char[] var7 = Character.toChars(var1); var5 >= var3; --var5) {
                    if(var4[var5] == var7[0]) {
                        if(var5 + 1 == var6) {
                            break;
                        }

                        if(var4[var5 + 1] == var7[1]) {
                            return var5 - this.offset;
                        }
                    }
                }
            }

            return -1;
        }
    }

    public int indexOf(String var1) {
        return this.indexOf(var1, 0);
    }

    public int indexOf(String var1, int var2) {
        return indexOf(this.value, this.offset, this.count, var1.value, var1.offset, var1.count, var2);
    }

    static int indexOf(char[] var0, int var1, int var2, char[] var3, int var4, int var5, int var6) {
        if(var6 >= var2) {
            return var5 == 0?var2:-1;
        } else {
            if(var6 < 0) {
                var6 = 0;
            }

            if(var5 == 0) {
                return var6;
            } else {
                char var7 = var3[var4];
                int var8 = var1 + (var2 - var5);

                for(int var9 = var1 + var6; var9 <= var8; ++var9) {
                    if(var0[var9] != var7) {
                        do {
                            ++var9;
                        } while(var9 <= var8 && var0[var9] != var7);
                    }

                    if(var9 <= var8) {
                        int var10 = var9 + 1;
                        int var11 = var10 + var5 - 1;

                        for(int var12 = var4 + 1; var10 < var11 && var0[var10] == var3[var12]; ++var12) {
                            ++var10;
                        }

                        if(var10 == var11) {
                            return var9 - var1;
                        }
                    }
                }

                return -1;
            }
        }
    }

    public int lastIndexOf(String var1) {
        return this.lastIndexOf(var1, this.count);
    }

    public int lastIndexOf(String var1, int var2) {
        return lastIndexOf(this.value, this.offset, this.count, var1.value, var1.offset, var1.count, var2);
    }

    static int lastIndexOf(char[] var0, int var1, int var2, char[] var3, int var4, int var5, int var6) {
        int var7 = var2 - var5;
        if(var6 < 0) {
            return -1;
        } else {
            if(var6 > var7) {
                var6 = var7;
            }

            if(var5 == 0) {
                return var6;
            } else {
                int var8 = var4 + var5 - 1;
                char var9 = var3[var8];
                int var10 = var1 + var5 - 1;
                int var11 = var10 + var6;

                while(true) {
                    while(var11 < var10 || var0[var11] == var9) {
                        if(var11 < var10) {
                            return -1;
                        }

                        int var12 = var11 - 1;
                        int var13 = var12 - (var5 - 1);
                        int var14 = var8 - 1;

                        do {
                            if(var12 <= var13) {
                                return var13 - var1 + 1;
                            }
                        } while(var0[var12--] == var3[var14--]);

                        --var11;
                    }

                    --var11;
                }
            }
        }
    }

    public String substring(int var1) {
        return this.substring(var1, this.count);
    }

    public String substring(int var1, int var2) {
        if(var1 < 0) {
            throw new StringIndexOutOfBoundsException(var1);
        } else if(var2 > this.count) {
            throw new StringIndexOutOfBoundsException(var2);
        } else if(var1 > var2) {
            throw new StringIndexOutOfBoundsException(var2 - var1);
        } else {
            return var1 == 0 && var2 == this.count?this:new String(this.offset + var1, var2 - var1, this.value);
        }
    }

    public CharSequence subSequence(int var1, int var2) {
        return this.substring(var1, var2);
    }

    public String concat(String var1) {
        int var2 = var1.length();
        if(var2 == 0) {
            return this;
        } else {
            char[] var3 = new char[this.count + var2];
            this.getChars(0, this.count, var3, 0);
            var1.getChars(0, var2, var3, this.count);
            return new String(0, this.count + var2, var3);
        }
    }

    public String replace(char var1, char var2) {
        if(var1 != var2) {
            int var3 = this.count;
            int var4 = -1;
            char[] var5 = this.value;
            int var6 = this.offset;

            do {
                ++var4;
            } while(var4 < var3 && var5[var6 + var4] != var1);

            if(var4 < var3) {
                char[] var7 = new char[var3];

                for(int var8 = 0; var8 < var4; ++var8) {
                    var7[var8] = var5[var6 + var8];
                }

                while(var4 < var3) {
                    char var9 = var5[var6 + var4];
                    var7[var4] = var9 == var1?var2:var9;
                    ++var4;
                }

                return new String(0, var3, var7);
            }
        }

        return this;
    }

    public boolean matches(String var1) {
        return Pattern.matches(var1, this);
    }

    public boolean contains(CharSequence var1) {
        return this.indexOf(var1.toString()) > -1;
    }

    public String replaceFirst(String var1, String var2) {
        return Pattern.compile(var1).matcher(this).replaceFirst(var2);
    }

    public String replaceAll(String var1, String var2) {
        return Pattern.compile(var1).matcher(this).replaceAll(var2);
    }

    public String replace(CharSequence var1, CharSequence var2) {
        return Pattern.compile(var1.toString(), 16).matcher(this).replaceAll(Matcher.quoteReplacement(var2.toString()));
    }

    public String[] split(String var1, int var2) {
        return Pattern.compile(var1).split(this, var2);
    }

    public String[] split(String var1) {
        return this.split(var1, 0);
    }

    public String toLowerCase(Locale var1) {
        if(var1 == null) {
            throw new NullPointerException();
        } else {
            int var2 = 0;

            int var4;
            while(true) {
                if(var2 >= this.count) {
                    return this;
                }

                char var3 = this.value[this.offset + var2];
                if(var3 >= '\ud800' && var3 <= '\udbff') {
                    var4 = this.codePointAt(var2);
                    if(var4 != Character.toLowerCase(var4)) {
                        break;
                    }

                    var2 += Character.charCount(var4);
                } else {
                    if(var3 != Character.toLowerCase(var3)) {
                        break;
                    }

                    ++var2;
                }
            }

            char[] var14 = new char[this.count];
            var4 = 0;
            System.arraycopy(this.value, this.offset, var14, 0, var2);
            String var5 = var1.getLanguage();
            boolean var6 = var5 == "tr" || var5 == "az" || var5 == "lt";

            int var10;
            for(int var11 = var2; var11 < this.count; var11 += var10) {
                int var9 = this.value[this.offset + var11];
                if((char)var9 >= '\ud800' && (char)var9 <= '\udbff') {
                    var9 = this.codePointAt(var11);
                    var10 = Character.charCount(var9);
                } else {
                    var10 = 1;
                }

                int var8;
                if(!var6 && var9 != 931) {
                    var8 = Character.toLowerCase(var9);
                } else {
                    var8 = ConditionalSpecialCasing.toLowerCaseEx(this, var11, var1);
                }

                if(var8 != -1 && var8 < 65536) {
                    var14[var11 + var4] = (char)var8;
                } else {
                    char[] var7;
                    if(var8 == -1) {
                        var7 = ConditionalSpecialCasing.toLowerCaseCharArray(this, var11, var1);
                    } else {
                        if(var10 == 2) {
                            var4 += Character.toChars(var8, var14, var11 + var4) - var10;
                            continue;
                        }

                        var7 = Character.toChars(var8);
                    }

                    int var12 = var7.length;
                    if(var12 > var10) {
                        char[] var13 = new char[var14.length + var12 - var10];
                        System.arraycopy(var14, 0, var13, 0, var11 + var4);
                        var14 = var13;
                    }

                    for(int var15 = 0; var15 < var12; ++var15) {
                        var14[var11 + var4 + var15] = var7[var15];
                    }

                    var4 += var12 - var10;
                }
            }

            return new String(0, this.count + var4, var14);
        }
    }

    public String toLowerCase() {
        return this.toLowerCase(Locale.getDefault());
    }

    public String toUpperCase(Locale var1) {
        if(var1 == null) {
            throw new NullPointerException();
        } else {
            int var4;
            for(int var2 = 0; var2 < this.count; var2 += var4) {
                int var3 = this.value[this.offset + var2];
                if(var3 >= '\ud800' && var3 <= '\udbff') {
                    var3 = this.codePointAt(var2);
                    var4 = Character.charCount(var3);
                } else {
                    var4 = 1;
                }

                int var5 = Character.toUpperCaseEx(var3);
                if(var5 == -1 || var3 != var5) {
                    char[] var14 = new char[this.count];
                    var4 = 0;
                    System.arraycopy(this.value, this.offset, var14, 0, var2);
                    String var15 = var1.getLanguage();
                    boolean var6 = var15 == "tr" || var15 == "az" || var15 == "lt";

                    int var10;
                    for(int var11 = var2; var11 < this.count; var11 += var10) {
                        int var9 = this.value[this.offset + var11];
                        if((char)var9 >= '\ud800' && (char)var9 <= '\udbff') {
                            var9 = this.codePointAt(var11);
                            var10 = Character.charCount(var9);
                        } else {
                            var10 = 1;
                        }

                        int var8;
                        if(var6) {
                            var8 = ConditionalSpecialCasing.toUpperCaseEx(this, var11, var1);
                        } else {
                            var8 = Character.toUpperCaseEx(var9);
                        }

                        if(var8 != -1 && var8 < 65536) {
                            var14[var11 + var4] = (char)var8;
                        } else {
                            char[] var7;
                            if(var8 == -1) {
                                if(var6) {
                                    var7 = ConditionalSpecialCasing.toUpperCaseCharArray(this, var11, var1);
                                } else {
                                    var7 = Character.toUpperCaseCharArray(var9);
                                }
                            } else {
                                if(var10 == 2) {
                                    var4 += Character.toChars(var8, var14, var11 + var4) - var10;
                                    continue;
                                }

                                var7 = Character.toChars(var8);
                            }

                            int var12 = var7.length;
                            if(var12 > var10) {
                                char[] var13 = new char[var14.length + var12 - var10];
                                System.arraycopy(var14, 0, var13, 0, var11 + var4);
                                var14 = var13;
                            }

                            for(int var16 = 0; var16 < var12; ++var16) {
                                var14[var11 + var4 + var16] = var7[var16];
                            }

                            var4 += var12 - var10;
                        }
                    }

                    return new String(0, this.count + var4, var14);
                }
            }

            return this;
        }
    }

    public String toUpperCase() {
        return this.toUpperCase(Locale.getDefault());
    }

    public String trim() {
        int var1 = this.count;
        int var2 = 0;
        int var3 = this.offset;

        char[] var4;
        for(var4 = this.value; var2 < var1 && var4[var3 + var2] <= 32; ++var2) {
            ;
        }

        while(var2 < var1 && var4[var3 + var1 - 1] <= 32) {
            --var1;
        }

        return var2 <= 0 && var1 >= this.count?this:this.substring(var2, var1);
    }

    public String toString() {
        return this;
    }

    public char[] toCharArray() {
        char[] var1 = new char[this.count];
        this.getChars(0, this.count, var1, 0);
        return var1;
    }

    public static String format(String var0, Object... var1) {
        return (new Formatter()).format(var0, var1).toString();
    }

    public static String format(Locale var0, String var1, Object... var2) {
        return (new Formatter(var0)).format(var1, var2).toString();
    }

    public static String valueOf(Object var0) {
        return var0 == null?"null":var0.toString();
    }

    public static String valueOf(char[] var0) {
        return new String(var0);
    }

    public static String valueOf(char[] var0, int var1, int var2) {
        return new String(var0, var1, var2);
    }

    public static String copyValueOf(char[] var0, int var1, int var2) {
        return new String(var0, var1, var2);
    }

    public static String copyValueOf(char[] var0) {
        return copyValueOf(var0, 0, var0.length);
    }

    public static String valueOf(boolean var0) {
        return var0?"true":"false";
    }

    public static String valueOf(char var0) {
        char[] var1 = new char[]{var0};
        return new String(0, 1, var1);
    }

    public static String valueOf(int var0) {
        return Integer.toString(var0, 10);
    }

    public static String valueOf(long var0) {
        return Long.toString(var0, 10);
    }

    public static String valueOf(float var0) {
        return Float.toString(var0);
    }

    public static String valueOf(double var0) {
        return Double.toString(var0);
    }

    public native String intern();

    private static class CaseInsensitiveComparator implements Comparator<String>, Serializable {
        private static final long serialVersionUID = 8575799808933029326L;

        private CaseInsensitiveComparator() {
        }

        public int compare(String var1, String var2) {
            int var3 = var1.length();
            int var4 = var2.length();
            int var5 = 0;

            for(int var6 = 0; var5 < var3 && var6 < var4; ++var6) {
                char var7 = var1.charAt(var5);
                char var8 = var2.charAt(var6);
                if(var7 != var8) {
                    var7 = Character.toUpperCase(var7);
                    var8 = Character.toUpperCase(var8);
                    if(var7 != var8) {
                        var7 = Character.toLowerCase(var7);
                        var8 = Character.toLowerCase(var8);
                        if(var7 != var8) {
                            return var7 - var8;
                        }
                    }
                }

                ++var5;
            }

            return var3 - var4;
        }
    }
}

