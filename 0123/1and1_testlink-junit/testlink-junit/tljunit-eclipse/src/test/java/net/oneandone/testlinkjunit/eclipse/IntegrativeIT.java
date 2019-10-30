/**
 * Copyright 1&1 Internet AG, https://github.com/1and1/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.oneandone.testlinkjunit.eclipse;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;

import net.oneandone.testlinkjunit.tljunit.SUTTestLinkRunListener;
import net.oneandone.testlinkjunit.tljunit.TestLinkLoggingRunListener;
import net.oneandone.testlinkjunit.tljunit.TestLinkXmlRunListener;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.slf4j.LoggerFactory;

public class IntegrativeIT {

    @Test
    public void testIntegrative() throws FileNotFoundException {
        final JUnitCore core = new JUnitCore();
        core.addListener(new TestLinkXmlRunListener());
        core.addListener(new TestLinkLoggingRunListener(LoggerFactory.getLogger("MYTESTLINK"), URI.create("http://testlink.sourceforge.net/demo/")));
        core.run(SUTTestLinkRunListener.class);
        assertTrue("Did not find logfile", new File("target/testlink.xml").exists());
    }
}
