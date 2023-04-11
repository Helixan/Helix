package org.cultro.roulette.lang;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Formatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RString {
    private final String value;

    public RString(RString value) {
        this.value = value.toString();
    }

    public RString(String value) {
        if (value == null) {
            this.value = "";
        } else {
            this.value = value;
        }
    }

    public RString(char[] values) {
        this.value = new String(values);
    }

    public RString() {
        this.value = "";
    }

    public int length() {
        return value.length();
    }

    public boolean isEmpty() {
        return value.isEmpty();
    }

    public char charAt(int index) {
        return value.charAt(index);
    }

    public int codePointAt(int index) {
        return value.codePointAt(index);
    }

    public int codePointBefore(int index) {
        return value.codePointBefore(index);
    }

    public int codePointCount(int beginIndex, int endIndex) {
        return value.codePointCount(beginIndex, endIndex);
    }

    public int offsetByCodePoints(int index, int codePointOffset) {
        return value.offsetByCodePoints(index, codePointOffset);
    }

    public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
        value.getChars(srcBegin, srcEnd, dst, dstBegin);
    }

    @Deprecated
    public void getBytes(int srcBegin, int srcEnd, byte[] dst, int dstBegin) {
        value.getBytes(srcBegin, srcEnd, dst, dstBegin);
    }

    public byte[] getBytes(String charsetName) throws UnsupportedEncodingException {
        return value.getBytes(charsetName);
    }

    public byte[] getBytes(Charset charset) {
        return value.getBytes(charset);
    }

    public byte[] getBytes() {
        return value.getBytes();
    }

    public boolean equals(Object anObject) {
        if (anObject instanceof String) {
            String input = (String) anObject;
            return input.equals(value);
        } else if (anObject instanceof RString) {
            RString input = (RString) anObject;
            return input.toString().equals(value);
        }
        return false;
    }

    public boolean contentEquals(StringBuffer sb) {
        return value.contentEquals(sb);
    }

    public boolean contentEquals(CharSequence cs) {
        return value.contentEquals(cs);
    }

    public boolean equalsIgnoreCase(String anotherString) {
        return value.equalsIgnoreCase(anotherString);
    }

    public int compareTo(String anotherString) {
        return value.compareTo(anotherString);
    }

    public int compareToIgnoreCase(String str) {
        return value.compareToIgnoreCase(str);
    }

    public boolean regionMatches(int toffset, String other, int ooffset, int len) {
        return value.regionMatches(toffset, other, ooffset, len);
    }

    public boolean regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len) {
        return value.regionMatches(ignoreCase, toffset, other, ooffset, len);
    }

    public boolean startsWith(String prefix, int toffset) {
        return value.startsWith(prefix, toffset);
    }

    public boolean startsWith(String prefix) {
        return value.startsWith(prefix);
    }

    public boolean endsWith(String suffix) {
        return value.endsWith(suffix);
    }

    public int hashCode() {
        return value.hashCode();
    }

    public int indexOf(int ch) {
        return value.indexOf(ch);
    }

    public int indexOf(int ch, int fromIndex) {
        return value.indexOf(ch, fromIndex);
    }

    public int lastIndexOf(int ch) {
        return value.lastIndexOf(ch);
    }

    public int lastIndexOf(int ch, int fromIndex) {
        return value.lastIndexOf(ch, fromIndex);
    }

    public int indexOf(String str) {
        return value.indexOf(str);
    }

    public int indexOf(String str, int fromIndex) {
        return value.indexOf(str, fromIndex);
    }

    public int lastIndexOf(String str) {
        return value.lastIndexOf(str);
    }

    public int lastIndexOf(String str, int fromIndex) {
        return value.lastIndexOf(str, fromIndex);
    }

    public RString substring(int beginIndex) {
        return new RString(value.substring(beginIndex));
    }

    public RString substring(int beginIndex, int endIndex) {
        return new RString(value.substring(beginIndex, endIndex));
    }

    public CharSequence subSequence(int beginIndex, int endIndex) {
        return value.substring(beginIndex, endIndex);
    }

    public RString concat(String str) {
        return new RString(value.concat(str));
    }

    public RString replace(char oldChar, char newChar) {
        return new RString(value.replace(oldChar, newChar));
    }

    public boolean matches(String regex) {
        return value.matches(regex);
    }

    public boolean contains(CharSequence s) {
        return value.contains(s);
    }

    public RString replaceFirst(String regex, String replacement) {
        return new RString(Pattern.compile(regex).matcher(value).replaceFirst(replacement));
    }

    public RString replaceAll(String regex, String replacement) {
        return new RString(Pattern.compile(regex).matcher(value).replaceAll(replacement));
    }

    public RString replace(CharSequence target, CharSequence replacement) {
        return new RString(Pattern.compile(target.toString(), Pattern.LITERAL).matcher(
                value).replaceAll(Matcher.quoteReplacement(replacement.toString())));
    }

    public RString[] split(String regex, int limit) {
        String[] arr1 = value.split(regex, limit);
        RString[] arr2 = new RString[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = new RString(arr1[i]);
        }
        return arr2;
    }

    public RString[] split(String regex) {
        return split(regex, 0);
    }


    public RString replaceIgnoreCase(String target, String replacement) {
        StringBuilder sbSource = new StringBuilder(value);
        StringBuilder sbSourceLower = new StringBuilder(value.toLowerCase());
        String searchString = target.toLowerCase();

        int idx = 0;
        while ((idx = sbSourceLower.indexOf(searchString, idx)) != -1) {
            sbSource.replace(idx, idx + searchString.length(), replacement);
            sbSourceLower.replace(idx, idx + searchString.length(), replacement);
            idx += replacement.length();
        }
        sbSourceLower.setLength(0);
        sbSourceLower.trimToSize();

        return new RString(sbSource.toString());
    }

    public RString replaceIgnoreCase(RString target, RString replacement) {
        return replaceIgnoreCase(target.toString(), replacement.toString());
    }

    public RString replaceIgnoreCase(String target, RString replacement) {
        return replaceIgnoreCase(target, replacement.toString());
    }

    public RString replaceIgnoreCase(RString target, String replacement) {
        return replaceIgnoreCase(target.toString(), replacement);
    }

    public boolean containsIgnoreCase(String target) {
        return value.toLowerCase().contains(target.toLowerCase());
    }

    public RString remove(String target) {
        return new RString(this.replace(target, ""));
    }

    public RString removeIgnoreCase(String target) {
        return new RString(this.replaceIgnoreCase(target, ""));
    }


    public RString bytesToHex() {
        byte[] in = value.getBytes(StandardCharsets.UTF_8);
        final char[] hexArray = "0123456789abcdef".toCharArray();

        char[] hexChars = new char[in.length * 2];
        for (int j = 0; j < in.length; j++) {
            int v = in[j] & 0xff;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0xf];
        }
        return new RString(hexChars);
    }

    public RString reverse() {
        char[] chars = value.toCharArray();
        ArrayUtils.reverse(chars);
        return new RString(chars);
    }

    public static RString join(CharSequence delimiter, CharSequence... elements) {
        return new RString(String.join(delimiter, elements));
    }

    public static RString join(CharSequence delimiter, Iterable<? extends CharSequence> elements) {
        return new RString(String.join(delimiter, elements));
    }

    public RString toLowerCase(Locale locale) {
        return new RString(value.toLowerCase(locale));
    }

    public RString toLowerCase() {
        return new RString(value.toLowerCase());
    }

    public RString toUpperCase(Locale locale) {
        return new RString(value.toUpperCase(locale));
    }

    public RString toUpperCase() {
        return new RString(value.toUpperCase());
    }

    public RString trim() {
        return new RString(value.trim());
    }

    @Override
    public String toString() {
        return value;
    }

    public char[] toCharArray() {
        return value.toCharArray();
    }

    public static RString format(String format, Object... args) {
        return new RString(new Formatter().format(format, args).toString());
    }

    public static RString format(Locale l, String format, Object... args) {
        return new RString(new Formatter(l).format(format, args).toString());
    }

    public static RString valueOf(Object obj) {
        return new RString((obj == null) ? "null" : obj.toString());
    }

    public static RString valueOf(char[] data) {
        return new RString(new String(data));
    }

    public static RString valueOf(char[] data, int offset, int count) {
        return new RString(new String(data, offset, count));
    }

    public static RString copyValueOf(char[] data, int offset, int count) {
        return new RString(new String(data, offset, count));
    }

    public static RString copyValueOf(char[] data) {
        return new RString(new String(data));
    }

    public static RString valueOf(boolean b) {
        return b ? new RString("true") : new RString("false");
    }

    public static RString valueOf(char c) {
        char[] data = {c};
        return new RString(new String(data));
    }

    public static RString valueOf(int i) {
        return new RString(Integer.toString(i));
    }

    public static RString valueOf(long l) {
        return new RString(Long.toString(l));
    }

    public static RString valueOf(float f) {
        return new RString(Float.toString(f));
    }

    public static RString valueOf(double d) {
        return new RString(Double.toString(d));
    }
}
