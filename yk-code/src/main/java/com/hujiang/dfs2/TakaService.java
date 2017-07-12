/**
 * 
 */
package com.hujiang.dfs2;


import java.util.Collection;
import com.hujiang.basic.framework.plugin.dfs.model.AdvanceFile;
import com.hujiang.basic.framework.plugin.dfs.model.SimpleFile;
import com.hujiang.basic.framework.plugin.dfs.model.UploadInfo;
import com.hujiang.basic.framework.plugin.dfs.service.QiniuProxy.Builder;

/**
 * @author yangkai
 *
 */
public interface TakaService {
    String getToken();
    
    Builder simple();
    Builder advance(String bucket);
 
    TakaService simple(SimpleFile file);
    TakaService simpleAll(Collection<SimpleFile> files);
    TakaService advance(AdvanceFile file);
    TakaService advanceAll(Collection<AdvanceFile> files);

    Collection<UploadInfo> simpleUpload();
    Collection<UploadInfo> advanceUpload();
    Collection<UploadInfo> simpleUpload(SimpleFile file);
    Collection<UploadInfo> simpleUpload(Collection<SimpleFile> files);
}
