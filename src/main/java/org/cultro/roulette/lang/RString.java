package org.cultro.roulette.lang;

import org.cultro.roulette.util.ArrayUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Formatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public final class RString {
    private final String value;

    public RString(final RString value) {
        this.value = value.toString();
    }

    public RString(final String value) {
        if (value == null) {
            this.value = "";
        } else {
            this.value = value;
        }
    }

    public RString(final char[] values) {
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
        } else if (anObject instanceof RString) {
            final RString input = (RString) anObject;
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

    public RString substring(final int beginIndex) {
        return new RString(value.substring(beginIndex));
    }

    public RString substring(final int beginIndex, final int endIndex) {
        return new RString(value.substring(beginIndex, endIndex));
    }

    public CharSequence subSequence(final int beginIndex, final int endIndex) {
        return value.substring(beginIndex, endIndex);
    }

    public RString concat(final String str) {
        return new RString(value.concat(str));
    }

    public RString replace(final char oldChar, final char newChar) {
        return new RString(value.replace(oldChar, newChar));
    }

    public boolean matches(final String regex) {
        return value.matches(regex);
    }

    public boolean contains(final CharSequence s) {
        return value.contains(s);
    }

    public RString replaceFirst(final String regex, final String replacement) {
        return new RString(Pattern.compile(regex).matcher(value).replaceFirst(replacement));
    }

    public RString replaceAll(final String regex, final String replacement) {
        return new RString(Pattern.compile(regex).matcher(value).replaceAll(replacement));
    }

    public RString replace(final CharSequence target, final CharSequence replacement) {
        return new RString(Pattern.compile(target.toString(), Pattern.LITERAL).matcher(
                value).replaceAll(Matcher.quoteReplacement(replacement.toString())));
    }

    public RString[] split(final String regex, final int limit) {
        final String[] arr1 = value.split(regex, limit);
        final RString[] arr2 = new RString[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = new RString(arr1[i]);
        }
        return arr2;
    }

    public RString[] split(String regex) {
        return split(regex, 0);
    }


    public RString replaceIgnoreCase(final String target, final String replacement) {
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

        return new RString(sbSource.toString());
    }

    public RString replaceIgnoreCase(final RString target, final RString replacement) {
        return replaceIgnoreCase(target.toString(), replacement.toString());
    }

    public RString replaceIgnoreCase(final String target, final RString replacement) {
        return replaceIgnoreCase(target, replacement.toString());
    }

    public RString replaceIgnoreCase(final RString target, final String replacement) {
        return replaceIgnoreCase(target.toString(), replacement);
    }

    public boolean containsIgnoreCase(final String target) {
        return value.toLowerCase().contains(target.toLowerCase());
    }

    public RString remove(final String target) {
        return new RString(this.replace(target, ""));
    }

    public RString removeIgnoreCase(final String target) {
        return new RString(this.replaceIgnoreCase(target, ""));
    }


    public RString bytesToHex() {
        final byte[] in = value.getBytes(StandardCharsets.UTF_8);
        final char[] hexArray = "0123456789abcdef".toCharArray();

        final char[] hexChars = new char[in.length * 2];
        for (int j = 0; j < in.length; j++) {
            final int v = in[j] & 0xff;
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

    public static RString format(final String format, final Object... args) {
        return new RString(new Formatter().format(format, args).toString());
    }

    public static RString format(final Locale l, final String format, final Object... args) {
        return new RString(new Formatter(l).format(format, args).toString());
    }

    public static RString valueOf(final Object obj) {
        return new RString((obj == null) ? "null" : obj.toString());
    }

    public static RString valueOf(final char[] data) {
        return new RString(new String(data));
    }

    public static RString valueOf(final char[] data, final int offset, final int count) {
        return new RString(new String(data, offset, count));
    }

    public static RString copyValueOf(final char[] data, final int offset, final int count) {
        return new RString(new String(data, offset, count));
    }

    public static RString copyValueOf(final char[] data) {
        return new RString(new String(data));
    }

    public static RString valueOf(final boolean b) {
        return b ? new RString("true") : new RString("false");
    }

    public static RString valueOf(final char c) {
        char[] data = {c};
        return new RString(new String(data));
    }

    public static RString valueOf(final int i) {
        return new RString(Integer.toString(i));
    }

    public static RString valueOf(final long l) {
        return new RString(Long.toString(l));
    }

    public static RString valueOf(final float f) {
        return new RString(Float.toString(f));
    }

    public static RString valueOf(final double d) {
        return new RString(Double.toString(d));
    }
}
