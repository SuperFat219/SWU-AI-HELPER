cmake_minimum_required(VERSION 3.21)
project(Huffman C)

set(CMAKE_C_STANDARD 99)
add_executable(Huffman main.c main_window.h main_window.c Encode.c huffman.h Decode.c)

find_package(PkgConfig REQUIRED)
pkg_check_modules(GTK3 REQUIRED gtk+-3.0)

include_directories(${GTK3_INCLUDE_DIRS})
link_directories(${GTK3_LIBRARY_DIRS})

add_definitions(${GTK3_CFLAGS_OTHER})

target_link_libraries(Huffman ${GTK3_LIBRARIES})
