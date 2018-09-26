'use strict';

let path = require('path');

var gulp = require('gulp');
var sass = require('gulp-sass');

let basedir = "../../..";

gulp.task('sass', function () {
    return gulp.src('./scss/main.scss')
        .pipe(sass().on('error', sass.logError))
        .pipe(gulp.dest(path.join(basedir, 'target/', '/classes', '/static', '/style')));
});

gulp.task('sass:watch', function () {
    gulp.watch('./scss/*.scss', gulp.series('sass'));
});

gulp.task('default', gulp.series('sass'));
