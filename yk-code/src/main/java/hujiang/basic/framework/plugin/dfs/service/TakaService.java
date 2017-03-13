/**
 * 
 */
package com.hujiang.basic.framework.plugin.dfs.service;


import java.util.List;

import com.hujiang.basic.framework.plugin.dfs.model.AdvanceFile;
import com.hujiang.basic.framework.plugin.dfs.model.SimpleFile;
import com.hujiang.basic.framework.plugin.dfs.model.UploadInfo;

/**
 * @author yangkai
 *
 */
public interface TakaService {
	
    /**
     * Upload file on simple way
     * @param file
     * @return
     */
    UploadInfo simpleUpload(SimpleFile file);

    /**
     * Batch Upload file on simple way
     * @param files
     * @return
     */
    List<UploadInfo> simpleUploadAll(List<SimpleFile> files);

    /**
     * Upload file on advanced way, with bucket & customKey & customPath
     * @param file
     * @return
     */
    UploadInfo advancedUpload(AdvanceFile file);

}
