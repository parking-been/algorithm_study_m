set -e

gh pr list \
  --state open \
  --search "label:submit sort:created-asc" \
  --json number \
| jq -r '.[].number' \
| while read pr; do
    echo "Attempting merge of PR #$pr"
    gh pr merge "$pr" --merge || {
      echo "Merge failed at PR #$pr"
      exit 1
    }
  done