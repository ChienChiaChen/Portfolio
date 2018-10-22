require 'rubygems'

task :run_travis_scripts do 
  print_travis_variables
  debug_build
  end
end

def debug_build
  puts "building release .."
  sh "./gradlew assembleDebug"
  puts "Debug build  was finished"
end

def print_travis_variables
  puts "print current building variables"
  sh "echo $TRAVIS_TAG"
  sh "echo $TRAVIS_BRANCH"
end

# methods

def is_tagged
  tag = ENV['TRAVIS_TAG']
  tag == "" ? false : true
end

def is_master_branch
    branch = ENV['TRAVIS_BRANCH']
    branch == "master" ? true : false
end

def is_develop_branch
    branch = ENV['TRAVIS_BRANCH']
    branch == "origin/develop" ? true : false
end
