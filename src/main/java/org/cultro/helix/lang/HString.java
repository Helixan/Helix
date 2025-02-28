package org.cultro.helix.lang;

import org.cultro.helix.util.ArrayUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Formatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public final class HString {
    private final String value;

    public HString(final HString value) {
        this.value = value.toString();
    }

    public HString(final String value) {
        if (value == null) {
            this.value = "";
        } else {
            this.value = value;
        }
    }

    public HString(final char[] values) {
        this.value = new String(values);
    }

    public HString() {
        this.value = "";
    }

    public int length() {
        return value.length();
    }

    public boolean isEmpty() {
        return value.isEmpty();
    }

    public char charAt(final int index) {
        return value.charAt(index);
    }

    public int codePointAt(final int index) {
        return value.codePointAt(index);
    }

    public int codePointBefore(final int index) {
        return value.codePointBefore(index);
    }

    public int codePointCount(final int beginIndex, final int endIndex) {
        return value.codePointCount(beginIndex, endIndex);
    }

    public int offsetByCodePoints(final int index, final int codePointOffset) {
        return value.offsetByCodePoints(index, codePointOffset);
    }

    public void getChars(final int srcBegin, final int srcEnd, final char[] dst, final int dstBegin) {
        value.getChars(srcBegin, srcEnd, dst, dstBegin);
    }

    @Deprecated
    public void getBytes(final int srcBegin, final int srcEnd, final byte[] dst, final int dstBegin) {
        value.getBytes(srcBegin, srcEnd, dst, dstBegin);
    }

    public byte[] getBytes(final String charsetName) throws UnsupportedEncodingException {
        return value.getBytes(charsetName);
    }

    public byte[] getBytes(final Charset charset) {
        return value.getBytes(charset);
    }

    public byte[] getBytes() {
        return value.getBytes();
    }

    public boolean equals(final Object anObject) {
        if (anObject instanceof String) {
            final String input = (String) anObject;
            return input.equals(value);
        } else if (anObject instanceof HString) {
            final HString input = (HString) anObject;
            return input.toString().equals(value);
        }
        return false;
    }

    public boolean contentEquals(final StringBuffer sb) {
        return value.contentEquals(sb);
    }

    public boolean contentEquals(final CharSequence cs) {
        return value.contentEquals(cs);
    }

    public boolean equalsIgnoreCase(final String anotherString) {
        return value.equalsIgnoreCase(anotherString);
    }

    public int compareTo(final String anotherString) {
        return value.compareTo(anotherString);
    }

    public int compareToIgnoreCase(final String str) {
        return value.compareToIgnoreCase(str);
    }

    public boolean regionMatches(final int toffset, final String other, final int ooffset, final int len) {
        return value.regionMatches(toffset, other, ooffset, len);
    }

    public boolean regionMatches(final boolean ignoreCase, final int toffset, final String other, final int ooffset, final int len) {
        return value.regionMatches(ignoreCase, toffset, other, ooffset, len);
    }

    public boolean startsWith(final String prefix, final int toffset) {
        return value.startsWith(prefix, toffset);
    }

    public boolean startsWith(final String prefix) {
        return value.startsWith(prefix);
    }

    public boolean endsWith(final String suffix) {
        return value.endsWith(suffix);
    }

    public int hashCode() {
        return value.hashCode();
    }

    public int indexOf(final int ch) {
        return value.indexOf(ch);
    }

    public int indexOf(final int ch, final int fromIndex) {
        return value.indexOf(ch, fromIndex);
    }

    public int lastIndexOf(final int ch) {
        return value.lastIndexOf(ch);
    }

    public int lastIndexOf(final int ch, final int fromIndex) {
        return value.lastIndexOf(ch, fromIndex);
    }

    public int indexOf(final String str) {
        return value.indexOf(str);
    }

    public int indexOf(final String str, final int fromIndex) {
        return value.indexOf(str, fromIndex);
    }

    public int lastIndexOf(final String str) {
        return value.lastIndexOf(str);
    }

    public int lastIndexOf(final String str, final int fromIndex) {
        return value.lastIndexOf(str, fromIndex);
    }

    public HString substring(final int beginIndex) {
        return new HString(value.substring(beginIndex));
    }

    public HString substring(final int beginIndex, final int endIndex) {
        return new HString(value.substring(beginIndex, endIndex));
    }

    public CharSequence subSequence(final int beginIndex, final int endIndex) {
        return value.substring(beginIndex, endIndex);
    }

    public HString concat(final String str) {
        return new HString(value.concat(str));
    }

    public HString replace(final char oldChar, final char newChar) {
        return new HString(value.replace(oldChar, newChar));
    }

    public boolean matches(final String regex) {
        return value.matches(regex);
    }

    public boolean contains(final CharSequence s) {
        return value.contains(s);
    }

    public HString replaceFirst(final String regex, final String replacement) {
        return new HString(Pattern.compile(regex).matcher(value).replaceFirst(replacement));
    }

    public HString replaceAll(final String regex, final String replacement) {
        return new HString(Pattern.compile(regex).matcher(value).replaceAll(replacement));
    }

    public HString replace(final CharSequence target, final CharSequence replacement) {
        return new HString(Pattern.compile(target.toString(), Pattern.LITERAL).matcher(
                value).replaceAll(Matcher.quoteReplacement(replacement.toString())));
    }

    public HString[] split(final String regex, final int limit) {
        final String[] arr1 = value.split(regex, limit);
        final HString[] arr2 = new HString[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = new HString(arr1[i]);
        }
        return arr2;
    }

    public HString[] split(String regex) {
        return split(regex, 0);
    }


    public HString replaceIgnoreCase(final String target, final String replacement) {
        final StringBuilder sbSource = new StringBuilder(value);
        final StringBuilder sbSourceLower = new StringBuilder(value.toLowerCase());
        final String searchString = target.toLowerCase();

        int index = 0;
        while ((index = sbSourceLower.indexOf(searchString, index)) != -1) {
            sbSource.replace(index, index + searchString.length(), replacement);
            sbSourceLower.replace(index, index + searchString.length(), replacement);
            index += replacement.length();
        }
        sbSourceLower.setLength(0);
        sbSourceLower.trimToSize();

        return new HString(sbSource.toString());
    }

    public HString replaceIgnoreCase(final HString target, final HString replacement) {
        return replaceIgnoreCase(target.toString(), replacement.toString());
    }

    public HString replaceIgnoreCase(final String target, final HString replacement) {
        return replaceIgnoreCase(target, replacement.toString());
    }

    public HString replaceIgnoreCase(final HString target, final String replacement) {
        return replaceIgnoreCase(target.toString(), replacement);
    }

    public boolean containsIgnoreCase(final String target) {
        return value.toLowerCase().contains(target.toLowerCase());
    }

    public HString remove(final String target) {
        return new HString(this.replace(target, ""));
    }

    public HString removeIgnoreCase(final String target) {
        return new HString(this.replaceIgnoreCase(target, ""));
    }


    public HString bytesToHex() {
        final byte[] in = value.getBytes(StandardCharsets.UTF_8);
        final char[] hexArray = "0123456789abcdef".toCharArray();

        final char[] hexChars = new char[in.length * 2];
        for (int j = 0; j < in.length; j++) {
            final int v = in[j] & 0xff;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0xf];
        }
        return new HString(hexChars);
    }

    public HString reverse() {
        char[] chars = value.toCharArray();
        ArrayUtils.reverse(chars);
        return new HString(chars);
    }

    public static HString join(CharSequence delimiter, CharSequence... elements) {
        return new HString(String.join(delimiter, elements));
    }

    public static HString join(CharSequence delimiter, Iterable<? extends CharSequence> elements) {
        return new HString(String.join(delimiter, elements));
    }

    public HString toLowerCase(Locale locale) {
        return new HString(value.toLowerCase(locale));
    }

    public HString toLowerCase() {
        return new HString(value.toLowerCase());
    }

    public HString toUpperCase(Locale locale) {
        return new HString(value.toUpperCase(locale));
    }

    public HString toUpperCase() {
        return new HString(value.toUpperCase());
    }

    public HString trim() {
        return new HString(value.trim());
    }

    @Override
    public String toString() {
        return value;
    }

    public char[] toCharArray() {
        return value.toCharArray();
    }

    public static HString format(final String format, final Object... args) {
        return new HString(new Formatter().format(format, args).toString());
    }

    public static HString format(final Locale l, final String format, final Object... args) {
        return new HString(new Formatter(l).format(format, args).toString());
    }

    public static HString valueOf(final Object obj) {
        return new HString((obj == null) ? "null" : obj.toString());
    }

    public static HString valueOf(final char[] data) {
        return new HString(new String(data));
    }

    public static HString valueOf(final char[] data, final int offset, final int count) {
        return new HString(new String(data, offset, count));
    }

    public static HString copyValueOf(final char[] data, final int offset, final int count) {
        return new HString(new String(data, offset, count));
    }

    public static HString copyValueOf(final char[] data) {
        return new HString(new String(data));
    }

    public static HString valueOf(final boolean b) {
        return b ? new HString("true") : new HString("false");
    }

    public static HString valueOf(final char c) {
        char[] data = {c};
        return new HString(new String(data));
    }

    public static HString valueOf(final int i) {
        return new HString(Integer.toString(i));
    }

    public static HString valueOf(final long l) {
        return new HString(Long.toString(l));
    }

    public static HString valueOf(final float f) {
        return new HString(Float.toString(f));
    }

    public static HString valueOf(final double d) {
        return new HString(Double.toString(d));
    }
}
