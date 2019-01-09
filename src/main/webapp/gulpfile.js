'use strict';

let path = require('path');

var gulp = require('gulp');
var sass = require('gulp-sass');
var cleanCSS = require('gulp-clean-css');
var sourcemaps = require('gulp-sourcemaps');
var minify = require('gulp-minify');

let basedir = "../../..";

gulp.task('sass', function () {
    return gulp.src('./scss/main.scss')
        .pipe(sass().on('error', sass.logError))
        .pipe(sourcemaps.init())
            .pipe(cleanCSS())
        .pipe(sourcemaps.write())
        .pipe(gulp.dest(path.join(basedir, '/target', '/classes', '/static', '/style')));
});

gulp.task('js', function() {
    return gulp.src('./js/**/*.js')
        .pipe(sourcemaps.init())
        .pipe(minify())
        .pipe(sourcemaps.write())
        .pipe(gulp.dest(path.join(basedir, '/target', '/classes', '/static', '/js')));
});

gulp.task('sass:watch', function () {
    gulp.watch('./scss/*.scss', gulp.series('sass'));
});

gulp.task('default', gulp.series(['sass', 'js']));
