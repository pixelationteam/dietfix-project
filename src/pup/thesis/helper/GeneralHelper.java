/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pup.thesis.helper;

/**
 *
 * @author Dell
 */
public class GeneralHelper {

    public static String getLastBitFromUrl(final String url) {
        // return url.replaceFirst("[^?]*/(.*?)(?:\\?.*)","$1);" <-- incorrect
        return url.replaceFirst(".*/([^/?]+).*", "$1");
    }
}
