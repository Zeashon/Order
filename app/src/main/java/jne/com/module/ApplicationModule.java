package jne.com.module;

import jne.com.context.AppContext;
import jne.com.module.library.InjectorModule;
import jne.com.module.library.NetworkProvider;
import jne.com.module.library.PersistenceProvider;
import jne.com.module.library.UtilProvider;

import dagger.Module;

@Module(
        injects = {
                AppContext.class,
        },
        includes = {
                UtilProvider.class,
                NetworkProvider.class,
                PersistenceProvider.class,
                InjectorModule.class,
        }
)
public class ApplicationModule {
}