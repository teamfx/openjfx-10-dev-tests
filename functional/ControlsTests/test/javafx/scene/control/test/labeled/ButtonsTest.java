/*
 * Copyright (c) 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation. Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package javafx.scene.control.test.labeled;

import javafx.scene.control.test.labeleds.ButtonsApp;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import test.javaclient.shared.FilteredTestRunner;

/**
 * Button control API test Uses {
 *
 * @javafx.scene.control.test.ButtonsApp} to render controls
 */
@RunWith(FilteredTestRunner.class)
public class ButtonsTest extends ButtonsBase {

//    @ScreenshotCheck
//    @Test(timeout=300000)
//    public void nodesTest() throws InterruptedException {
//        testCommons(Pages.Nodes.name());
//    }
//    /**
//     * Test for specific API
//     */
//    @ScreenshotCheck
//    @Test(timeout=300000)
//    public void buttonsTest() throws InterruptedException {
//        testCommons(Pages.Buttons.name());
//    }
    //Util
    @BeforeClass
    public static void runUI() {
        ButtonsApp.main(null);
    }

    @Override
    protected String getName() {
        return "ButtonsTest";
    }

    @Override
    protected String getEllipsingString() {
        final String oldLookAndFeelName = "caspian";
        final String lfProp = System.getProperty("javafx.userAgentStylesheetUrl");
        if (null != lfProp) {
            if (0 == oldLookAndFeelName.compareTo(lfProp)) {
                return ButtonsApp.ELLIPSING_STRING;
            }
        }
        return ButtonsApp.ELLIPSING_STRING_MODENA;
    }
}