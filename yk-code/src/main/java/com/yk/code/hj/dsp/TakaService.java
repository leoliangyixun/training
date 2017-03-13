/**
 * 
 */
package com.yk.code.hj.dsp;


import java.util.Collection;
import com.hujiang.basic.framework.plugin.dfs.model.AdvanceFile;
import com.hujiang.basic.framework.plugin.dfs.model.SimpleFile;
import com.hujiang.basic.framework.plugin.dfs.model.UploadInfo;

/**
 * @author yangkai
 *
 */
public interface TakaService {
	String getToken();

    TakaService simple(SimpleFile file);
    TakaService simpleAll(Collection<SimpleFile> files);
    TakaService advance(AdvanceFile file);
    TakaService advanceAll(Collection<AdvanceFile> files);
    
    /**
     * Batch Upload files on simple way, upload files to default bucket.
     * @return
     */
    Collection<UploadInfo> simpleUpload();
    
    /**
     * Upload files on advanced way, with bucket
     * @return
     */
    Collection<UploadInfo> advanceUpload();
    
    /**
     * Batch Upload file single on simple way, upload files to default bucket.
     * @return
     */
    Collection<UploadInfo> simpleUpload(SimpleFile file);
    
    /**
     * Batch Upload files batch on simple way, upload files to default bucket.
     * @return
     */
    Collection<UploadInfo> simpleUpload(Collection<SimpleFile> files);
}
