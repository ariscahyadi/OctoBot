## Octo-Pro Installation and Configuration

It is utilizing an Ansible playbook to provision the Kubernetes Master and Worker.

### Download the Source Code

Download the whole source of OctoBot software from the GitHub.

```console
ncl@orchestrator:~/$ git clone https://github.com/nus-ncl/OctoBot
ncl@orchestrator:~/$ cd OctoBot/Octo-Pro
```

### Provisioning File (Specification)
This **file** defines the machine how many machines are required to build the environment and defines which machine need to be a orchestrator or to be a worker. Currently, it is only define the Ansible's [hosts](hosts) file.

The example of Ansible's hosts file with one orchestrator and two worker machines:
```console
ncl@orchestrator $ cat hosts
[master]
127.0.0.1   ansible_connection=local ansible_user=ncl

[worker]
10.0.0.214  ansible_connection=ssh ansible_user=ncl
10.0.0.215  ansible_connection=ssh ansible_user=ncl
``` 
### Execute the Provisioning Script
Run the provisioning script with this following command:
```console
ncl@orchestrator:~/OctoBot/Octo-Pro$ ./setup.sh
```
For example to install the nodes, please type "w" and it will give this following output:
```console 
Please select your installation target? [Controller (c), Worker(w), All (a) or Exit (e)] w

Installing Worker Nodes

PLAY [worker] **********************************************************************************************************

TASK [Gathering Facts] *************************************************************************************************
ok: [10.0.0.215]

TASK [Install packages that allow apt to be used over HTTPS] ***********************************************************
 [WARNING]: Could not find aptitude. Using apt-get instead

ok: [10.0.0.215]

TASK [Add an apt signing key for Docker] *******************************************************************************
ok: [10.0.0.215]

TASK [Add apt repository for stable version] ***************************************************************************
ok: [10.0.0.215]

TASK [Install docker and its dependecies] ******************************************************************************
ok: [10.0.0.215]

TASK [Add current "ncl" to docker group] *******************************************************************************
ok: [10.0.0.215]

TASK [Remove swapfile from /etc/fstab] *********************************************************************************
ok: [10.0.0.215] => (item=swap)
ok: [10.0.0.215] => (item=none)

TASK [Disable swap] ****************************************************************************************************
skipping: [10.0.0.215]

TASK [Add an apt signing key for Kubernetes] ***************************************************************************
ok: [10.0.0.215]

TASK [Adding apt repository for Kubernetes] ****************************************************************************
ok: [10.0.0.215]

TASK [Install Kubernetes binaries] *************************************************************************************
ok: [10.0.0.215]

TASK [Restart kubelet] *************************************************************************************************
changed: [10.0.0.215]

TASK [Copy the join command to server location] ************************************************************************
ok: [10.0.0.215]

TASK [Join the node to cluster] ****************************************************************************************
ok: [10.0.0.215]

PLAY RECAP *************************************************************************************************************
10.0.0.215                 : ok=13   changed=1    unreachable=0    failed=0    skipped=0    rescued=0    ignored=0   

```

### Check the Provisioning Result
Check the result of the provisioning script with this following command:

```console
ncl@orchestrator:~/OctoBot/Octo-Pro$ kubectl get nodes -o wide
```
If the provisoning is successful, it will show this following output:
```console
NAME          STATUS   ROLES    AGE   VERSION   INTERNAL-IP    EXTERNAL-IP   OS-IMAGE             KERNEL-VERSION      CONTAINER-RUNTIME
k8s-master    Ready    master   47h   v1.16.0   10.0.0.209   <none>        Ubuntu 18.04.2 LTS   4.15.0-54-generic   docker://18.9.0
worker-3      Ready    <none>   47h   v1.16.0   10.0.0.210   <none>        Ubuntu 18.04.2 LTS   4.15.0-64-generic   docker://18.9.0
worker-node   Ready    <none>   47h   v1.16.0   10.0.0.215   <none>        Ubuntu 18.04.2 LTS   4.15.0-54-generic   docker://18.9.0

```
