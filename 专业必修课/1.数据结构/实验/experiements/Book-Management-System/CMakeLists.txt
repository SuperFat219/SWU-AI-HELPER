cmake_minimum_required(VERSION 3.21)
project(book_system C)

set(CMAKE_C_STANDARD 11)

include_directories(.)

add_executable(book_system
        main.c
        sources/mainWindow.c
        sources/common.c
        headers/mainWindow.h
        headers/common.h sources/View_window.c headers/View_window.h sources/search_window.c headers/search_window.h sources/data_operations.c headers/data_operations.h sources/Add_window.c headers/Add_window.h sources/view_readers_window.c headers/view_readers_window.h sources/login_window.c headers/login_window.h sources/user_window.c headers/user_window.h sources/borrow_window.c headers/borrow_window.h sources/Enter_window.c headers/Enter_window.h sources/user_borrow_window.c headers/user_borrow_window.h sources/user_return_window.c headers/user_return_window.h sources/user_person_window.c headers/user_person_window.h)
find_package(PkgConfig REQUIRED)
pkg_check_modules(GTK3 REQUIRED gtk+-3.0)

include_directories(${GTK3_INCLUDE_DIRS})
link_directories(${GTK3_LIBRARY_DIRS})

add_definitions(${GTK3_CFLAGS_OTHER})

target_link_libraries(book_system ${GTK3_LIBRARIES})