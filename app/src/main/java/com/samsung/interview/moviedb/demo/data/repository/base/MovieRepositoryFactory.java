package com.samsung.interview.moviedb.demo.data.repository.base;

import android.content.Context;

import com.samsung.interview.moviedb.demo.Utils;
import com.samsung.interview.moviedb.demo.data.api.TMDbApiSync;
import com.samsung.interview.moviedb.demo.data.exception.NetworkConnectionException;
import com.samsung.interview.moviedb.demo.data.mapper.CursorModelEntitiesDataMapper;
import com.samsung.interview.moviedb.demo.data.mapper.DTOModelEntitiesDataMapper;
//import com.samsung.interview.moviedb.demo.data.repository.LocalMovieRepository;
import com.samsung.interview.moviedb.demo.data.repository.TMDbMovieRepository;

/**
 * This class knows how to construct each type of the repositories
 * Created by licha on 3/24/2018.
 */

public class MovieRepositoryFactory {

    /**
     * Create and return an instance of a TMDbMovieRepository
     * @return
     */
    public static ICloudMovieRepository getCloudRepository(Context context) throws NetworkConnectionException {
        if(!Utils.isConnected(context))
            throw new NetworkConnectionException();
        return new TMDbMovieRepository(new TMDbApiSync(), new DTOModelEntitiesDataMapper());
    }


//DTOModelEntitiesDataMapper    /**
//     * Create and return an instance of a LocalMovieRepository
//     * @param ctx
//     * @return
//     */
//    public static ILocalMovieRepository getLocalRepository(Context ctx) {
//        return new LocalMovieRepository(ctx, new CursorModelEntitiesDataMapper());
//    }
}

