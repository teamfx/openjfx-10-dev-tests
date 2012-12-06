/*
 * Copyright (c) 2009, 2012, Oracle and/or its affiliates. All rights reserved.
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

package org.jemmy.fx.control;

import javafx.scene.web.WebView;
import org.jemmy.fx.SceneDock;
import org.jemmy.fx.control.WebViewWrap.ByAttribute;
import org.jemmy.fx.control.WebViewWrap.ByName;
import org.jemmy.lookup.LookupCriteria;
import org.jemmy.samples.SampleBase;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WebViewTest extends SampleBase {
    static SceneDock scene;
    
    @BeforeClass
    public static void launch() throws InterruptedException {
        startApp(WebViewApp.class);
        scene = new SceneDock();
    }

    @Before
    public void reset() {
    }

    @Test
    public void test() {
        WebViewDock web_view = new WebViewDock(scene.asParent(), new LookupCriteria<WebView>() {
            public boolean check(WebView cntrl) {
                return true;
            }
        });

        WebNodeDock body = new WebNodeDock(web_view.asWebNodeParent(), new ByName("BODY"));
        
        WebNodeDock button = new WebNodeDock(body.asWebNodeParent(), new ByAttribute("type", "button"));

        button.wrap().mouse().click();
    }
}