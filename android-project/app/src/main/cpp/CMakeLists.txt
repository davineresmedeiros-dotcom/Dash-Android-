cmake_minimum_required(VERSION 3.4.1)

project(launcherfix VERSION ${GEODE_VERSION} LANGUAGES C CXX)

# Add source files
set(SOURCE_FILES
    LauncherFix.cpp
)

# Create shared library
add_library(launcherfix SHARED ${SOURCE_FILES})

# Set output name - will be loaded as liblauncherfix.so
set_target_properties(launcherfix PROPERTIES
    OUTPUT_NAME "launcherfix"
    PREFIX "")

# Install target
if (NOT GEODE_BUILDING_DOCS)
    execute_process(
        COMMAND ${GEODE_CLI} profile path -d
        OUTPUT_VARIABLE GEODE_PROFILE_GD_PATH
        RESULT_VARIABLE GEODE_PROFILE_GD_PATH_RESULT
    )
    if (${GEODE_PROFILE_GD_PATH_RESULT} EQUAL 0)
        string(STRIP ${GEODE_PROFILE_GD_PATH} GEODE_PROFILE_GD_PATH)
        install(
            TARGETS launcherfix
            RUNTIME
            DESTINATION "${GEODE_PROFILE_GD_PATH}"
        )
    endif()
endif()
