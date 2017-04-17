package jne.com.module.library;

import android.content.Context;

import jne.com.context.AppCookie;
import jne.com.module.qualifiers.ApplicationContext;
import jne.com.module.qualifiers.CacheDirectory;
import jne.com.module.qualifiers.ShareDirectory;
import jne.com.network.RestApiClient;
import jne.com.util.Constants;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
    library = true,
    includes = ContextProvider.class
)
public class NetworkProvider {

    @Provides @Singleton
    public RestApiClient provideRestApiClient(@CacheDirectory File cacheLocation, @ApplicationContext Context context) {
        RestApiClient restApiClient = new RestApiClient(context, cacheLocation);
        if (AppCookie.isLoggin()) {
            restApiClient.setToken(AppCookie.getAccessToken());
        }
        return restApiClient;
    }

    @Provides @Singleton @CacheDirectory
    public File provideHttpCacheLocation(@ApplicationContext Context context) {
        return context.getCacheDir();
    }

    @Provides @Singleton @ShareDirectory
    public File provideShareLocation(@ApplicationContext Context context) {
        return new File(context.getFilesDir(), Constants.Persistence.SHARE_FILE);
    }
}
