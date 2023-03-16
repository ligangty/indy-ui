/**
 * Copyright (C) 2011-2022 Red Hat, Inc. (https://github.com/Commonjava/indy)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.commonjava.indy.ui.conf;

import org.commonjava.propulsor.config.annotation.ConfigName;
import org.commonjava.propulsor.config.annotation.SectionName;

import javax.enterprise.context.ApplicationScoped;
import java.io.File;

@SectionName( ContentBrowseConfig.SECTION )
@ApplicationScoped
public class ContentBrowseConfig
{
    public static final String SECTION = "content-browse";

    public static final String ENABLED_PARAM = "enabled";

    private static final Boolean DEFAULT_ENABLED = Boolean.TRUE;

    public static final String CONTENT_BROWSE_UI_DIR_PARAM = "content.browse.ui.dir";

    private static final File DEFAULT_UI_DIR =
            new File( System.getProperty( "indy-ui.home", "/var/lib/indy-ui" ), "ui/content-browse" );

    private Boolean enabled;

    private File contentBrowseUIDir;

    public ContentBrowseConfig()
    {
    }

    public boolean isEnabled()
    {
        return enabled == null ? DEFAULT_ENABLED : enabled;
    }

    @ConfigName( ContentBrowseConfig.ENABLED_PARAM )
    public void setEnabled( final boolean enabled )
    {
        this.enabled = enabled;
    }

    public File getContentBrowseUIDir()
    {
        return contentBrowseUIDir == null ? DEFAULT_UI_DIR : contentBrowseUIDir;
    }

    @ConfigName( ContentBrowseConfig.CONTENT_BROWSE_UI_DIR_PARAM )
    public void setContentBrowseUIDir( File contentBrowseUIDir )
    {
        this.contentBrowseUIDir = contentBrowseUIDir;
    }

}
