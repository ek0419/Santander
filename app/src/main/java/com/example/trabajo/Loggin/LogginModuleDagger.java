package com.example.trabajo.Loggin;

import dagger.Module;
import dagger.Provides;

@Module
public class LogginModuleDagger {
    @Provides
   public LogginFragmentInterface.Presenter providePresenter(LogginFragmentInterface.Model model)
    {
        return new LogginFragmentPresenter(model);
    }

    @Provides
    public LogginFragmentInterface.Model provideModel(LogginFragmentInterface.LogginRepository repository)
    {
        return new LogginFragmentModel(repository);

    }


    @Provides
    public LogginFragmentInterface.LogginRepository provideRepository()
    {
        return new MemoryRepository(); //cambiar aqui el objeto para el almacenamiento en bs o firebase  tc
    }

}
