#!/bin/bash

# Prompt the user for a commit message
echo "Enter commit message:"
read commitMessage

# Run git commands
git add -A
git commit -m "$commitMessage"
git push
echo done
