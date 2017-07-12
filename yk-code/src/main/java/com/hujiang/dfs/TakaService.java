/**
 * 
 */
package com.hujiang.dfs;


import java.util.List;

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
