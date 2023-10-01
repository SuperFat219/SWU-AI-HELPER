cmake_minimum_required(VERSION 3.21)
project(traffic_information_system C)

set(CMAKE_C_STANDARD 11)
include_directories(.)

add_executable(traffic_information_system
        main.c
        source/enter_window.c
        source/common.c
        header/enter_window.h
        header/enter_window.h source/main_window.c header/main_window.h source/plane_window.c header/plane_window.h header/train_window.h source/train_window.c)
find_package(PkgConfig REQUIRED)
pkg_check_modules(GTK3 REQUIRED gtk+-3.0)

include_directories(${GTK3_INCLUDE_DIRS})
link_directories(${GTK3_LIBRARY_DIRS})

add_definitions(${GTK3_CFLAGS_OTHER})

target_link_libraries(traffic_information_system ${GTK3_LIBRARIES})