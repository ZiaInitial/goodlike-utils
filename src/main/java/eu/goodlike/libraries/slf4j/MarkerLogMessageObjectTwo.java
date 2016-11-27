package eu.goodlike.libraries.slf4j;

import org.slf4j.Logger;
import org.slf4j.Marker;

/**
 * @see Log
 */
@FunctionalInterface
public interface MarkerLogMessageObjectTwo {

    void log(Logger logger, Marker marker, String format, Object arg1, Object arg2);

}
