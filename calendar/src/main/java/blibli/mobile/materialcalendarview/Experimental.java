package blibli.mobile.materialcalendarview;

/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License. Inspired from
 * https://code.google.com/p/guava-libraries/source/browse/guava/src/com/google/common/annotations/
 * Beta.java
 */

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Signifies that a public API (public class, method or field) is will almost certainly be changed
 * or removed in a future release. An API bearing this annotation should not be used or relied upon
 * in production code. APIs exposed with this annotation exist to allow broad testing and feedback
 * on experimental features.
 **/
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.FIELD,
    ElementType.METHOD, ElementType.TYPE})
@Documented
@Experimental
public @interface Experimental {
}
